package jl.multithread;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.SleepingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.nio.ByteBuffer;

public class DisruptorDemo {
    public static void main(String[] args) throws Exception {
        Disruptor<LongEvent> disruptor = new Disruptor<>(LongEvent::new, 128,
                new ThreadFactoryBuilder().setNameFormat("disruptor-").build()
                , ProducerType.MULTI, new SleepingWaitStrategy());

        disruptor.handleEventsWith(new ProcessingEventHandler())
                .then(new ClearingEventHandler());
        disruptor.start();
        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();
        LongEventProducer producer = new LongEventProducer(ringBuffer);
        ByteBuffer bb = ByteBuffer.allocate(8);
        for (long l = 0; true; l++) {
            bb.putLong(0, l);
            producer.onData(bb);
            Thread.sleep(1000);
        }
    }

    private static class ProcessingEventHandler implements EventHandler<LongEvent> {
        @Override
        public void onEvent(LongEvent event, long sequence, boolean endOfBatch) throws Exception {
            System.out.println("processing event:" + event);
        }
    }

    private static class ClearingEventHandler implements EventHandler<LongEvent> {
        @Override
        public void onEvent(LongEvent event, long sequence, boolean endOfBatch) throws Exception {
            System.out.println("clean event " + event);
        }
    }

    private static class LongEventProducer {
        private final RingBuffer<LongEvent> ringBuffer;

        public LongEventProducer(RingBuffer<LongEvent> ringBuffer) {
            this.ringBuffer = ringBuffer;
        }

        public void onData(ByteBuffer bb) {
            ringBuffer.publishEvent((event, sequence, buffer) -> event.set(buffer.getLong(0)), bb);
        }
    }

    private static class LongEvent {
        private long val;

        public void set(long val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return "longEvent:" + val;
        }
    }
}
