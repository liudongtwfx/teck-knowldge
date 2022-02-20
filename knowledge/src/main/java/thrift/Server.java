package thrift;

import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.THsHaServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TSSLTransportFactory;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.layered.TFramedTransport;
import thrift.gen.tutorial.Calculator;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@Slf4j
public class Server {

    public static CalculatorHandler handler;

    public static Calculator.Processor<Calculator.Iface> processor;

    public static void main(String[] args) {
        try {
            handler = new CalculatorHandler();
            Calculator.Iface iFace = (Calculator.Iface) Proxy.newProxyInstance(Server.class.getClassLoader(),
                    new Class[]{Calculator.Iface.class}, new CalculatorServerProxy(handler));
            processor = new Calculator.Processor<>(iFace);

            Runnable simple = () -> simple(processor);
            Runnable secure = () -> secure(processor);
            new Thread(() -> nonblocking(processor)).start();
            new Thread(simple).start();
            new Thread(secure).start();
        } catch (Exception x) {
            x.printStackTrace();
        }
    }

    public static void simple(Calculator.Processor<Calculator.Iface> processor) {
        try {
            TServerSocket serverTransport = new TServerSocket(9090);
            TSimpleServer server = new TSimpleServer(new TServer.Args(serverTransport).processor(processor));
            // Use this for a multithreaded server
            // TServer server = new TThreadPoolServer(new TThreadPoolServer.Args(serverTransport).processor(processor));

            System.out.println("Starting the simple server...");
            server.serve();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void nonblocking(Calculator.Processor<Calculator.Iface> processor) {
        try {
            TNonblockingServerSocket serverTransport = new TNonblockingServerSocket(9092);
            TFramedTransport.Factory transport = new TFramedTransport.Factory();
            TBinaryProtocol.Factory protocol = new TBinaryProtocol.Factory();
            THsHaServer server = new THsHaServer(new THsHaServer.Args(serverTransport).processor(processor)
                    .transportFactory(transport)
                    .protocolFactory(protocol)
                    .inputTransportFactory(new TFramedTransport.Factory())
                    .outputTransportFactory(new TFramedTransport.Factory()));
            // Use this for a multithreaded server
            // TServer server = new TThreadPoolServer(new TThreadPoolServer.Args(serverTransport).processor(processor));

            System.out.println("Starting the nonblocking server...");
            server.serve();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void secure(Calculator.Processor<Calculator.Iface> processor) {
        try {
            /*
             * Use TSSLTransportParameters to setup the required SSL parameters. In this example
             * we are setting the keystore and the keystore password. Other things like algorithms,
             * cipher suites, client auth etc can be set.
             */
            TSSLTransportFactory.TSSLTransportParameters params = new TSSLTransportFactory.TSSLTransportParameters();
            // The Keystore contains the private key
            params.setKeyStore("source/lib_java_test_.keystore", "thrift", null, null);

            /*
             * Use any of the TSSLTransportFactory to get a server transport with the appropriate
             * SSL configuration. You can use the default settings if properties are set in the command line.
             * Ex: -Djavax.net.ssl.keyStore=.keystore and -Djavax.net.ssl.keyStorePassword=thrift
             *
             * Note: You need not explicitly call open(). The underlying server socket is bound on return
             * from the factory class.
             */
            TServerTransport serverTransport = TSSLTransportFactory.getServerSocket(9091, 0, null, params);
            TServer server = new TSimpleServer(new TServer.Args(serverTransport).processor(processor));

            // Use this for a multi threaded server
            // TServer server = new TThreadPoolServer(new TThreadPoolServer.Args(serverTransport).processor(processor));

            System.out.println("Starting the secure server...");
            server.serve();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static class CalculatorServerProxy implements InvocationHandler {
        private final CalculatorHandler handler;

        public CalculatorServerProxy(CalculatorHandler handler) {
            this.handler = handler;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            log.info("before calculate");
            long start = System.currentTimeMillis();
            Thread.sleep(100);
            Object result = method.invoke(this.handler, args);
            log.info("result completed: in {} ms", System.currentTimeMillis() - start);
            return result;
        }
    }
}
