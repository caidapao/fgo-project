package com.caidapao.fgo.commons.utils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by caixuan on 2018/7/20.
 * Time 10:10
 */
public class DownLoadFile {

    public static void main(String[] args) {


        for (int i = 1; i < 223; i++) {
            String fileId = "";
            if (10 > i) {
                fileId = "00" + i;
            } else if (i < 100) {
                fileId = "0" + i;
            }else{
                fileId = i + "";
            }
            if(i == 83 || i == 149 || i == 151|| i==168 ){
                continue;
            }
            String path = "https://img.fgowiki.com/fgo/card/servant/" + fileId + "D.jpg";
            URL url = null;

            //从网络上下载一张图片
            InputStream inputStream = null;
            OutputStream outputStream = null;
            //建立一个网络链接
            HttpURLConnection con = null;
            try {
                url = new URL(path);
                con = (HttpURLConnection) url.openConnection();
                inputStream = con.getInputStream();
                outputStream = new FileOutputStream(new File("E:\\fgoServantD\\" + i + "D.jpg"));
                int n = -1;
                byte b[] = new byte[1024];
                while ((n = inputStream.read(b)) != -1) {
                    outputStream.write(b, 0, n);
                }
                outputStream.flush();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    inputStream.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        }

    }

}
