package com.caidapao.fgo.module.servant.provider;

import com.caidapao.fgo.commons.utils.JsonUtils;
import com.caidapao.fgo.commons.utils.WXHttpUtil;
import com.caidapao.fgo.module.commons.base.provider.BaseProvider;
import com.caidapao.fgo.module.servant.entity.Servant;
import com.caidapao.fgo.module.servant.service.ServantService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by caixuan on 2018/8/25.
 * Time 12:23
 */
@RequestMapping("/mp/tool/servant")
@RestController
public class MpToolServantProvider extends BaseProvider {

    @Autowired
    private ServantService servantService;


    @GetMapping("/getFgoServantList")
    public Object getFgoServantList() {
        for (int i = 1; i < 1; i++) {
            String responseJson = WXHttpUtil.doGet("https://api.umowang.com/guides/data/fgo?command=pets_list_all&page=" + i + "&params=");
            HashMap responseMap = JsonUtils.toBean(responseJson, HashMap.class);
            System.out.println(responseMap);
            List<LinkedHashMap<String, Object>> servants = (List<LinkedHashMap<String, Object>>) responseMap.get("data");
            for (LinkedHashMap<String, Object> linkedHashMap : servants) {
                if (linkedHashMap != null) {
                    Servant servant = new Servant();
                    servant.setServantId(((Integer) linkedHashMap.get("id")).longValue());
                    servant.setChineseName((String) linkedHashMap.get("name"));
                    servant.setJapaneseName((String) linkedHashMap.get("name_jp"));
                    servant.setEnglishName((String) linkedHashMap.get("name_en"));
                    servant.setClassCode((String) linkedHashMap.get("class"));
                    servant.setBaseHp(((Integer) linkedHashMap.get("lv1_hp")).longValue());
                    servant.setBaseDamage(((Integer) linkedHashMap.get("lv1_atk")).longValue());
                    servant.setMaxHp(((Integer) linkedHashMap.get("lvmax4_hp")).longValue());
                    servant.setMaxDamage(((Integer) linkedHashMap.get("lvmax4_atk")).longValue());
                    servant.setNoblePhantasmType((String) linkedHashMap.get("t_prop"));
//                    servantService.save(servant);
                }
            }
        }
        return handleSuccess();
    }

    @GetMapping("/getFgoServantDetail")
    public Object getFgoServantDetail() {
        for (int i = 1; i < 2; i++) {
            System.out.println("目前进行到："+i);
            String url1 = "https://fgowiki.com/guide/petdetail?p=pc";   //传入你所要爬取的页面地址
            InputStream is = null;  //创建输入流用于读取流
            BufferedReader br = null; //包装流,加快读取速度
            StringBuilder html = new StringBuilder(); //用来保存读取页面的数据.
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
                for (Element element : elements) {
                    if(element.data().length() > 0){
                        String detail = element.data();
                        if (detail.contains("];//console.log(datadetail);")) {
                            detail = detail.substring(18, detail.indexOf("];//console.log(datadetail);"));
                            LinkedHashMap<String, Object> result = JsonUtils.toBean(detail, LinkedHashMap.class);
                            System.out.println(result);
                            if (result != null) {
                                Servant servant = servantService.findOne(Long.valueOf((String)result.get("ID")));
                                servant.setCvName((String) result.get("CV"));
                                servant.setPainter((String) result.get("ILLUST"));
                                servant.setNoblePhantasmName((String) result.get("T_NAME"));
                                servant.setStar((String) result.get("STAR"));
                                servant.setLucky((String) result.get("LUCKY"));
                                servant.setCardBuster((String) result.get("CARD_BUSTER"));
                                servant.setCardArts((String) result.get("CARD_ARTS"));
                                servant.setCardQuick((String) result.get("CARD_QUICK"));
                                servant.setHeight((String) result.get("Height"));
                                servant.setWeight((String) result.get("Weight"));
                                servant.setRegion((String) result.get("Region"));
                                servant.setGender((String) result.get("Gender"));
                                servant.setAttributes((String) result.get("Attributes"));
                                servant.setDetailStr(detail);
//                                servantService.save(servant);
                            }
                        }
                    }

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return handleSuccess();
    }



}
