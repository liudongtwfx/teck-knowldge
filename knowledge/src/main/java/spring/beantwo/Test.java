package spring.beantwo;

import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;

/**
 * @author liudong
 */
@Component
public class Test {
    public static void main(String[] args) {
        String shortName = ClassUtils.getShortName(Test.class);
        System.out.println(shortName);
        System.out.println(Test.class.getName());
    }
}
