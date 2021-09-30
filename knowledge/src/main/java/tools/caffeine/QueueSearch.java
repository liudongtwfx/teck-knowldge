package tools.caffeine;


import tools.caffeine.queue.MpscGrowableArrayQueue;
import tools.caffeine.queue.UnsafeAccess;

public class QueueSearch {
    public static void main(String[] args) throws Exception {
        MpscGrowableArrayQueue<Integer> queue = new MpscGrowableArrayQueue<>(16, 200);
        for (int i = 0; i < 100; i++) {
            queue.offer(i);
        }

        System.out.println(UnsafeAccess.objectFieldOffset(MpscGrowableArrayQueue.class, "producerLimit"));
    }
}
