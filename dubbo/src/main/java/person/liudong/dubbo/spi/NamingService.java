package person.liudong.dubbo.spi;

import org.apache.dubbo.common.extension.Adaptive;
import org.apache.dubbo.common.extension.SPI;

@SPI(value = "naming")
public interface NamingService {
    @Adaptive
    String loadName();
}
