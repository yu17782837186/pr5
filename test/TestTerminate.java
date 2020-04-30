package cn.com.state;
//终止线程两种方法
//1 线程正常执行完毕  有限的次数
//2 外部干涉  加入标识
public class TestTerminate implements Runnable{
//    private boolean flag = true;
//    private String name;
//
//    public TestTerminate(String name) {
//        this.name = name;
//    }
//
//    @Override
//    public void run() {
//        int i = 0;
//        while(flag) {
//            System.out.println(name+"-->"+i++);
//        }
//    }
//    public void terminate() {
//        this.flag = false;
//    }
//    public static void main(String[] args) {
//        TestTerminate tt = new TestTerminate("小红");
//        new Thread(tt).start();
//        for (int i = 0; i <= 99; i++) {
//            if (i ==88) {
//                tt.terminate();
//                System.out.println("tt线程终止");
//            }
//            System.out.println("main--->"+i);
//        }
//    }
    private boolean flag = true;
    private String name;

    public TestTerminate(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        int i = 0;
        while(flag) {
            System.out.println(name+"-->"+i++);
        }
    }
    public void terminate() {
        this.flag = false;
    }
    public static void main(String[] args) {
        TestTerminate tt= new TestTerminate("小红");
        new Thread(tt).start();
        for (int i = 0; i <= 100; i++) {
            if (i == 66) {
                tt.terminate();
                System.out.println("tt线程终止");
            }
            System.out.println("main-->"+i);
        }
    }
}
