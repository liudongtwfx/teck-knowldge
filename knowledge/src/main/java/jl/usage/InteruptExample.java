package jl.usage;

/**
 * 中断是一种协作机制
 * <p>
 * Java的中断是一种协作机制，也就是说通过中断并不能直接STOP另外一个线程，而需要被中断的线程自己处理中断，即仅给了另一个线程一个中断标识，由线程自行处理。
 *
 * @author liudong
 */
public class InteruptExample {

    public static void main(String[] args) {
        example();
    }

    static void example() {
        try {
            MyThread thread = new MyThread();
            thread.start();
            Thread.sleep(20);
            thread.interrupt();//请求中断MyThread线程
        } catch (InterruptedException e) {
            System.out.println("main catch");
            e.printStackTrace();
        }
        System.out.println("end!");
    }

    public static class MyThread extends Thread {
        @Override
        public void run() {
            try {
                super.run();
                for (int i = 0; i < 500000; i++) {
                    // 检测中断异常
                    if (interrupted()) {
                        System.out.println("should be stopped and exit");
                        throw new InterruptedException();
                    }
                    System.out.println("i=" + (i + 1));
                }
                System.out.println("this line is also executed. thread does not stopped");//尽管线程被中断,但并没有结束运行。这行代码还是会被执行
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }


    static class Runner implements Runnable {
        private static final Object LOCK = new Object();

        @Override
        public void run() {
            synchronized (LOCK) {
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
