
// 从Thread派生一个自定义类，然后覆写run()方法：
public class ThreadTest  extends Thread{
    @Override
    public void run() {
        System.out.println("start new a thread");
    }

    public static void main(String[] args) {
        Thread t = new ThreadTest();
        t.start();

        Thread t2 = new Thread(() -> {
            System.out.println("start new thread");
        });
        t2.start();
    }
}
