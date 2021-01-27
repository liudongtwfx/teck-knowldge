package jl.multithread;

import lombok.Data;
import lombok.SneakyThrows;

import java.util.concurrent.locks.StampedLock;

public class StamptedLockExample {
    final static StampedLock stampedLock = new StampedLock();

    public static void main(String[] args) throws Exception {
        DemoClass demoClass = new DemoClass();
        for (int i = 0; i < 1000; i++) {
            new Thread(new Read(demoClass)).start();
            new Thread(new UpdateVersion(demoClass)).start();
        }
        Thread.sleep(1000);
        System.out.println("version:" + demoClass.getVersion());
    }

    static class Read implements Runnable {
        final DemoClass demoClass;

        Read(DemoClass demoClass) {
            this.demoClass = demoClass;
        }

        @SneakyThrows
        @Override
        public void run() {
            long readStamped = stampedLock.tryOptimisticRead();
            if (!stampedLock.validate(readStamped)) {
                stampedLock.tryConvertToReadLock(readStamped);
                System.out.println(demoClass.version);
            }
        }
    }

    static class UpdateVersion implements Runnable {
        private final DemoClass demoClass;

        UpdateVersion(DemoClass demoClass) {
            this.demoClass = demoClass;
        }

        @Override
        public void run() {
            long writeStamped = stampedLock.writeLock();
            demoClass.version++;
            stampedLock.unlockWrite(writeStamped);
        }
    }

    @Data
    private static class DemoClass {
        private int version;
    }
}
