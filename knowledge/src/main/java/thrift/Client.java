package thrift;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.async.TAsyncClientManager;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.transport.*;
import org.apache.thrift.transport.layered.TFramedTransport;
import thrift.gen.shared.SharedStruct;
import thrift.gen.tutorial.Calculator;
import thrift.gen.tutorial.InvalidOperation;
import thrift.gen.tutorial.Operation;
import thrift.gen.tutorial.Work;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Client {
    public static void main(String[] args) throws Exception {
        ExecutorService executorService = new ThreadPoolExecutor(10, 20, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10));
        List<CompletableFuture<Void>> completableFutures = new ArrayList<>();
        int total = 30;
        long start = System.currentTimeMillis();
        for (int i = 0; i < total; i++) {
            completableFutures.add(CompletableFuture.runAsync(new ClientRunner("simple"), executorService));
        }
        CompletableFuture.allOf(completableFutures.toArray(new CompletableFuture[total])).get();
        System.out.println("total cost:" + (System.currentTimeMillis() - start));
        System.exit(1);
    }

    private interface Invoker {
        void invoke() throws Exception;
    }

    private interface Callback<T> {
        void callback(T result);
    }

    @Slf4j
    private static class ClientRunner implements Runnable {
        private final String mode;

        public ClientRunner(String mode) {
            this.mode = mode;
        }

        @Override
        public void run() {
            try {
                TProtocolFactory protocolFactory = new TBinaryProtocol.Factory();
                final TNonblockingTransport transport = new TNonblockingSocket("localhost", 9092);
                final TAsyncClientManager clientManager = new TAsyncClientManager();
                final Calculator.AsyncClient asyncClient = new Calculator.AsyncClient(protocolFactory, clientManager, transport);

                performAsync(asyncClient);

                // transport.close();
            } catch (Exception x) {
                log.error("ClientRunner occur exception", x);
            }
        }

        private TTransport getByMode() throws TException, IOException {
            if (mode.equals("simple")) {
                TSocket localhost = new TSocket("localhost", 9090);
                localhost.open();
                return localhost;
            }

            if (mode.equals("nonblocking")) {
                TProtocolFactory protocolFactory = new TBinaryProtocol.Factory();
                final TNonblockingTransport transport = new TNonblockingSocket("localhost", 9092);
                final TAsyncClientManager clientManager = new TAsyncClientManager();
                final Calculator.AsyncClient asyncClient = new Calculator.AsyncClient(protocolFactory, clientManager, transport);
                TNonblockingSocket tNonblockingSocket = new TNonblockingSocket("localhost", 9092);
                tNonblockingSocket.startConnect();
                tNonblockingSocket.finishConnect();
                return new TFramedTransport(tNonblockingSocket);
            }

            /*
             * Similar to the server, you can use the parameters to setup client parameters or
             * use the default settings. On the client side, you will need a TrustStore which
             * contains the trusted certificate along with the public key.
             * For this example it's a self-signed cert.
             */
            TSSLTransportFactory.TSSLTransportParameters params = new TSSLTransportFactory.TSSLTransportParameters();
            params.setTrustStore("source/lib_java_test_.keystore", "thrift", "SunX509", "JKS");
            /*
             * Get a client transport instead of a server transport. The connection is opened on
             * invocation of the factory method, no need to specifically call open()
             */
            return TSSLTransportFactory.getClientSocket("localhost", 9091, 0, params);
        }

        @SneakyThrows
        private void perform(Calculator.Client client) throws TException {
            client.ping();
            // Thread.sleep(100);
            log.info("ping()");

            int sum = client.add(1, 1);
            log.info("1+1=" + sum);

            Work work = new Work();

            work.op = Operation.DIVIDE;
            work.num1 = 1;
            work.num2 = 2;
            try {
                int quotient = client.calculate(1, work);
                log.info("Whoa we can divide by 0,    " + quotient);
            } catch (InvalidOperation io) {
                log.info("Invalid operation: " + io.why);
            }

            work.op = Operation.SUBTRACT;
            work.num1 = 15;
            work.num2 = 10;
            try {
                int diff = client.calculate(1, work);
                log.info("15-10=" + diff);
            } catch (InvalidOperation io) {
                log.info("Invalid operation: " + io.why);
            } catch (TException e) {
                log.error("TException", e);
            }

            try {
                SharedStruct struct = client.getStruct(1);
                log.info("Check struct: " + struct.value);
            } catch (Exception e) {
                log.error("exception", e);
            }
        }


        @SneakyThrows
        private void performAsync(Calculator.AsyncClient client) {
            CountDownLatch latch = new CountDownLatch(1);
            client.ping(new AsyncMethodCallbackAdapter<>(latch, result -> log.info("ping")));

            latch.await();
            latch = new CountDownLatch(1);
            client.add(1, 1, new AsyncMethodCallbackAdapter<>(latch, result -> log.info("1+1=" + result)));

            latch.await();
            latch = new CountDownLatch(1);
            Work work = new Work();
            work.op = Operation.DIVIDE;
            work.num1 = 1;
            work.num2 = 2;
            client.calculate(1, work, new AsyncMethodCallbackAdapter<>(latch, response -> log.info("Whoa we can divide by 0,    " + response)));

            latch.await();
            latch = new CountDownLatch(1);
            work.op = Operation.SUBTRACT;
            work.num1 = 15;
            work.num2 = 10;
            client.calculate(1, work, new AsyncMethodCallbackAdapter<>(latch, response -> log.info("15-10=" + response)));

            latch.await();
            latch = new CountDownLatch(1);
            client.getStruct(1, new AsyncMethodCallbackAdapter<>(latch, response -> log.info("Check struct: " + response)));
            latch.await();
        }

        private Runnable wrapRunnable(Invoker invoker) {
            return () -> {
                try {
                    invoker.invoke();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            };
        }
    }

    public static class AsyncMethodCallbackAdapter<T> implements AsyncMethodCallback<T> {
        public final CountDownLatch countDownLatch;
        private final Callback<T> callback;

        public AsyncMethodCallbackAdapter(CountDownLatch countDownLatch, Callback<T> callback) {
            this.countDownLatch = countDownLatch;
            this.callback = callback;
        }

        @Override
        public void onComplete(T response) {
            try {
                callback.callback(response);
            } finally {
                countDownLatch.countDown();
            }
        }

        @Override
        public void onError(Exception exception) {

        }
    }
}
