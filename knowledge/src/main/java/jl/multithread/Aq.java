package jl.multithread;

import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.ReentrantLock;

public class Aq {
    private static Object object = new Object();

    public static void main(String[] args) {
        Thread sync1 = new Thread(() -> {
            while (true) {
                try {
                    Field sync = ReentrantLock.class.getDeclaredField("sync");
                    ReflectionUtils.makeAccessible(sync);
                    AbstractQueuedSynchronizer o = (AbstractQueuedSynchronizer) sync.get(Runner.lock);
                    Thread firstQueuedThread = o.getFirstQueuedThread();
                    if (firstQueuedThread != null) {
                        System.out.println(firstQueuedThread.getName());
                        firstQueuedThread.interrupt();
                    }
                    // Thread.sleep(10);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        sync1.start();
        for (int i = 0; i < 10; i++) {
            new Thread(new Runner()).start();
        }
    }

    public void method() {
        // **************************Synchronized的使用方式**************************
// 1.用于代码块
        synchronized (this) {
        }
// 2.用于对象
        synchronized (object) {
        }

// 4.可重入
        for (int i = 0; i < 100; i++) {
            synchronized (this) {
            }
        }
    }

    // 3.用于方法
    public synchronized void testSync() {
    }

    // **************************ReentrantLock的使用方式**************************
    public void test() throws Exception {
        // 1.初始化选择公平锁、非公平锁
        ReentrantLock lock = new ReentrantLock(true);
        // 2.可用于代码块
        lock.lock();
        try {
            try {
                // 3.支持多种加锁方式，比较灵活; 具有可重入特性
                if (lock.tryLock(100, TimeUnit.MILLISECONDS)) {
                }
            } finally {
                // 4.手动释放锁
                lock.unlock();
            }
        } finally {
            lock.unlock();
        }
    }

    private static class Runner implements Runnable {
        private static final MyAqs myAqs = new MyAqs();
        private static final ReentrantLock lock = new ReentrantLock(true);
        private final long executeTime = System.currentTimeMillis() + 1000;

        @Override
        public void run() {
            System.out.println("runner running");
            while (System.currentTimeMillis() < executeTime) {

            }
            lock.lock();
            try {
                System.out.println("getLock:" + Thread.currentThread().getName());
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    private static class MyAqs extends ReentrantLock {

    }
}
