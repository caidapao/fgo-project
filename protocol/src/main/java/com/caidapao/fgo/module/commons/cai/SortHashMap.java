package com.caidapao.fgo.module.commons.cai;

import java.util.*;

/**
 * Created by caixuan on 2018/10/19.
 * Time 16:00
 */
public class SortHashMap extends Thread {

    private static ThreadLocal<Integer> count = new ThreadLocal<>();

    public static void main(String args[]) {

//        HashMap<Integer, User> users = new HashMap<>();
//        users.put(1, new User("caidapao24", 24));
//        users.put(3, new User("caidapao23", 23));
//        users.put(2, new User("caidapao25", 25));
//        System.out.println(users);
//
//        HashMap<Integer, User> sortedMap = sortMap(users);
//        System.out.println(sortedMap);

        List<Integer> list = new ArrayList<>();
//        list.add(5);
        System.out.println(list);


        int[] arr = {1, 4, 1, 4, 2, 5, 4, 5, 8, 7, 8, 77, 88, 5, 4, 9, 6, 2, 4, 1, 5};

//        LinkedHashMap<Integer,Integer> sort = new LinkedHashMap<>();
//        for(int i=0;i<arr.length;i++){
//            if(sort.containsKey(arr[i])){
//                sort.put(arr[i],sort.get(arr[i])+1);
//            }else{
//                sort.put(arr[i],1);
//            }
//        }
        int[] brr = new int[123];

        for (int i = 0; i < arr.length; i++) {
            brr[arr[i]]++;
        }



        for (int i = 0; i < brr.length; i++) {
            if (brr[i] != 0) {
                System.out.println("字符：" + i + "出现" + brr[i] + "次");
            }
        }


//        new Thread() {
//            @Override
//            public synchronized void run() {
//                while (true) {
//                    try {
//                        if (count.get() == null) {
//                            count.set(1);
//                        }
//                        System.out.println("我是一号线程,目前的执行次数：" + count.get());
//                        count.set(count.get() + 1);
//                        if (count.get() > 10) {
//                            notifyAll();
//                        }
//                        Thread.sleep(1000);
//
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }.start();


//        new Thread(new Runnable() {
//            @Override
//            public synchronized void run() {
//                while (true) {
//                    try {
//                        if (count.get() == null || count.get() < 10) {
//                            wait();
//                        }
//                        Thread.sleep(2000);
//                        System.out.println("我是二号线程");
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//
//            }
//        }).start();
    }


    private static HashMap<Integer, User> sortMap(HashMap<Integer, User> users) {
        Set<Map.Entry<Integer, User>> entrySet = users.entrySet();
        List<Map.Entry<Integer, User>> list = new ArrayList<>(entrySet);
        list.sort(Comparator.comparingInt(o -> o.getValue().getAge()));
        LinkedHashMap<Integer, User> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<Integer, User> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
    }


    @Override
    public void run() {
        super.run();
    }
}
