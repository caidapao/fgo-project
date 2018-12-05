package com.caidapao.fgo.module.commons.cai;

/**
 * Created by caixuan on 2018/10/20.
 * Time 22:48
 */
public class ShareData {

    private int num = 10;

    public synchronized void inc() {
        num++;
        System.out.println(Thread.currentThread().getName() + "invoke inc method num=" + num);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
