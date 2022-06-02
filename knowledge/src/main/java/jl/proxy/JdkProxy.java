package jl.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JdkProxy {
    public static void main(String[] args) {
        Service service = new ServiceImpl();
        Service proxy = (Service) Proxy.newProxyInstance(service.getClass().getClassLoader(), new Class[]{Service.class}, new InvocationHandlerImpl(service));
        proxy.print("hi");
    }

    interface Service {
        void print(String s);
    }

    static class ServiceImpl implements Service {

        @Override
        public void print(String s) {
            System.out.println("hello " + s);
        }
    }


    private static class InvocationHandlerImpl implements InvocationHandler {
        private Service service;

        public InvocationHandlerImpl(Service service) {
            this.service = service;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println(proxy.getClass().getName());
            System.out.println(this == proxy);
            return method.invoke(this.service, args);
        }
    }
}
