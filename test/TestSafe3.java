package cn.com.state;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
public class TestSafe3 {
    public static void main(String[] args) {
        //并发容器 不需要加锁
        CopyOnWriteArrayList<String> list2 = new CopyOnWriteArrayList<>();
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            new Thread(()->{
                //同步块 锁list
//                synchronized(list) {
//                    list.add(Thread.currentThread().getName());
//                }
                list2.add(Thread.currentThread().getName());
            }).start();
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(list2.size());
    }
}
