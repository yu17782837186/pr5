package cn.com.state;

public class TestSafe implements Runnable{
    private int ticketNum = 10;
    private boolean flag = true;
    @Override
    public void run() {
        while(flag) {
            //Thread.yield();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //test1();
            //test2();
            //test3();
            //test4();
            test5();
        }
    }
    //线程安全 同步 成员的方法锁的是this 即这个对象
    public synchronized void test1() {
        if (ticketNum <= 0) {
            this.flag = false;
            return;
        }
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"-->"+ticketNum--);
    }
    public void test2() {
        //范围太大 性能低下
        //同步块  锁对象 这个对象是this 与锁test2()这个方法一样
        synchronized(this) {
            if (ticketNum <= 0) {
                this.flag = false;
                return;
            }
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "-->" + ticketNum--);
        }
    }
    public void test3() {
        //线程不安全 ticketNum对象在变
        synchronized((Integer)ticketNum) {
            if (ticketNum <= 0) {
                this.flag = false;
                return;
            }
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "-->" + ticketNum--);
        }
    }
    public void test4() {
        //线程不安全 范围太小锁不住
        synchronized(this) {
            if (ticketNum <= 0) {
                this.flag = false;
                return;
            }
        }
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"-->"+ticketNum--);
    }
    public void test5() {
        //提高了性能 double checking 双重检测
        //尽可能锁定合理的范围（不是指代码，而是指数据的完整性）
        if (ticketNum <= 0) { //考虑的是没有票的情况
            this.flag = false;
            return;
        }
        synchronized (this) {
            if (ticketNum <= 0) { //考虑的是最后一张票的情况下
                this.flag = false;
                return;
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "-->" + ticketNum--);
        }
    }
    public static void main(String[] args) {
        TestSafe ts = new TestSafe();
        new Thread(ts,"小明").start();
        new Thread(ts,"小红").start();
        new Thread(ts,"小亮").start();
    }
}
