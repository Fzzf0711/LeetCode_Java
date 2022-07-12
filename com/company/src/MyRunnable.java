// 实现runnable接口
public class MyRunnable implements Runnable {
    public static void main(String[] args) {
        Thread t = new Thread(new MyRunnable());
        t.start();
    }

    @Override
    public void run() {
        System.out.println("start new 啊 是 thread");
    }
}
