package person.liudong.dubbo.spi;

import org.apache.dubbo.rpc.model.ApplicationModel;

public class DubboSpiExample {
    public static void main(String[] args) {
        NamingService loadedExtensions = ApplicationModel.defaultModel()
                .getExtensionLoader(NamingService.class).getDefaultExtension();
        System.out.println(loadedExtensions.loadName());
    }
}
