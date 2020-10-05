import lombok.extern.slf4j.Slf4j;
import lombok.var;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class Main {
    private static final String NAME = "Main";

    public static void main(String[] args) throws Exception {
        a();
        for (int i = 0; i < 100; i++) {
            System.out.println("args = " + Arrays.deepToString(args));
            log.info("test");
        }
    }

    private static boolean a() {
        while (b()) {
            return false;
        }
        return true;
    }

    private static boolean b() {
        //while (a()) {
        var s = "123";
        List<String> strs = new ArrayList<>();
        strs.add("123");
        System.out.println(s.length());
        //}
        return true;
    }
}
