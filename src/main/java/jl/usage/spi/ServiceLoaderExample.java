package jl.usage.spi;

import java.util.ServiceLoader;

public class ServiceLoaderExample {
    public static void main(String[] args) {
        ServiceLoader<IService> load = ServiceLoader.load(IService.class);

        load.forEach(IService::print);
    }
}
