package jl.usage.spi;

public class ServiceImpl implements IService {
    @Override
    public void print() {
        System.out.println("print service" + ServiceImpl.class.getName());
    }
}
