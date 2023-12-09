package person.liudong.dubbo.service.api;

public interface GreetingServiceAsync extends GreetingService {
    String asyncSayHello(String name);
}
