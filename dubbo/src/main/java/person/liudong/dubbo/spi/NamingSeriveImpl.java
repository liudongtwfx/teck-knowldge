package person.liudong.dubbo.spi;

public class NamingSeriveImpl implements NamingService{
    @Override
    public String loadName() {
        return "liudong";
    }
}
