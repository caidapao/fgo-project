package com.caidapao.fgo.commons.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.LinkedHashMap;

/**
 * 抓页面工具类
 */
public class CatchPageUtil {

    public static void main(String[] args) {
        String url1 = "https://fgowiki.com/guide/petdetail/2?p=pc";   //传入你所要爬取的页面地址
        InputStream is = null;  //创建输入流用于读取流
        BufferedReader br = null; //包装流,加快读取速度
        StringBuffer html = new StringBuffer(); //用来保存读取页面的数据.
        String temp = ""; //创建临时字符串用于保存每一次读的一行数据，然后html调用append方法写入temp;
        try {
            URL url2 = new URL(url1); //获取URL;
            is = url2.openStream(); //打开流，准备开始读取数据;
            br = new BufferedReader(new InputStreamReader(is)); //将流包装成字符流，调用br.readLine()可以提高读取效率，每次读取一行;
            while ((temp = br.readLine()) != null) {//读取数据,调用br.readLine()方法每次读取一行数据,并赋值给temp,如果没数据则值==null,跳出循环;
                html.append(temp); //将temp的值追加给html,这里注意的时String跟StringBuffere的区别前者不是可变的后者是可变的;
            }
            is.close();
            is = null;

            Document doc = Jsoup.parse(html.toString()); //通过Jsoup解析页面,生成一个document对象;
            Elements elements = doc.getElementsByTag("script");//通过class的名字得到（即XX）,一个数组对象Elements里面有我们想要的数据,至于这个div的值呢你打开浏览器按下F12就知道了;
            for(Element element:elements){
                String detail = element.childNode(0).attr("data");
                if(detail.contains("];//console.log(datadetail);")){
                    detail = detail.substring(18,detail.indexOf("];//console.log(datadetail);"));
                    LinkedHashMap<String,Object> result = JsonUtils.toBean(detail,LinkedHashMap.class);
                    System.out.println(result);
                }
            }

        } catch (IOException e) {

            e.printStackTrace();
        }
    }
}
