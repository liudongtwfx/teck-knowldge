import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;

public class FinalTest {
    private final int value;

    public FinalTest(int value) {
        this.value = value;
    }

    public static void main(String[] args) throws Exception {
        FinalTest finalTest = new FinalTest(1);
        Field field = finalTest.getClass().getDeclaredField("value");
        ReflectionUtils.makeAccessible(field);
        ReflectionUtils.setField(field,finalTest,10);
        System.out.println(finalTest.value);
    }
}
