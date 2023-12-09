package person.liudong.dubbo.service.api;


import person.liudong.dubbo.PoJo;
import person.liudong.dubbo.Result;

public interface GreetingService {
    String sayHello(String name);

    Result<String> testGeneric(PoJo poJo);
}
