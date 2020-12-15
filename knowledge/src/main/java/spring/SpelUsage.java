package spring;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class SpelUsage {
    public static void main(String[] args) throws Exception {
        a();
    }

    private static void a() throws NoSuchMethodException {
        // Create and set a calendar
        GregorianCalendar c = new GregorianCalendar();
        c.set(1856, 7, 9);

        // The constructor arguments are name, birthday, and nationality.
        Inventor tesla = new Inventor("Nikola Tesla", c.getTime(), "Serbian", new Person("liudong"));
        Map<String, Object> map = new HashMap<>();
        map.put("name", "liudong");
        ExpressionParser parser = new SpelExpressionParser();
        Expression exp = parser.parseExpression("create.createBy");
        //EvaluationContext context = new StandardEvaluationContext(tesla);

        StandardEvaluationContext context = new StandardEvaluationContext(map);
        context.registerFunction("reverseString",
                StringUtils.class.getDeclaredMethod("isNotBlank", CharSequence.class));

        Object helloWorldReversed =
                parser.parseExpression("#map.get('name')").getValue(context, Object.class);
        System.out.println(helloWorldReversed);
        String name = (String) exp.getValue(context);
        System.out.println(name);
    }

    @AllArgsConstructor
    @Data
    public static class Inventor {
        private String name;
        private Date birthday;
        private String nationality;
        private Person create;
    }

    @Data
    @AllArgsConstructor
    public static class Person {
        private String createBy;
    }
}
