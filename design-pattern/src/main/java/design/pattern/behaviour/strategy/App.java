package design.pattern.behaviour.strategy;

/**
 * 策略模式定义一族算法类，将每个算法分别封装起来，让它们可以互相替换。策略模式可以使算法的变化独立于使用它们的客户端（这里的客户端代指使用算法的代码）。策略模式用来解耦策略的定义、创建、使用。
 * 实际上，一个完整的策略模式就是由这三个部分组成的。
 * 1、策略类的定义比较简单，包含一个策略接口和一组实现这个接口的策略类。
 * 2、策略的创建由工厂类来完成，封装策略创建的细节。
 * 3、策略模式包含一组策略可选，客户端代码如何选择使用哪个策略，
 * 有两种确定方法：编译时静态确定和运行时动态确定。其中，“运行时动态确定”才是策略模式最典型的应用场景。
 * 除此之外，我们还可以通过策略模式来移除 if-else 分支判断。实际上，这得益于策略工厂类，更本质上点讲，是借助“查表法”，根据 type 查表替代根据 type 分支判断。
 */
public class App {
    public static void main(String[] args) {
        LearningStrategy learningStrategy = getLearningStrategy(LeaningTools.BOOK, "java 8");
        learningStrategy.learn();
    }

    private static LearningStrategy getLearningStrategy(LeaningTools tools, Object... args) {
        try {
            Class<?>[] argsCLazz = new Class[args.length];
            for (int i = 0; i < args.length; i++) {
                argsCLazz[i] = args[i].getClass();
            }
            return tools.getLearningStrategy().getConstructor(argsCLazz).newInstance(args);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    enum LeaningTools {
        BOOK(ReadingStrategy.class), WBSITE(WebLeaningStrategy.class);

        private final Class<? extends LearningStrategy> learningStrategy;

        LeaningTools(Class<? extends LearningStrategy> learningStrategy) {
            this.learningStrategy = learningStrategy;
        }

        public Class<? extends LearningStrategy> getLearningStrategy() {
            return learningStrategy;
        }
    }
}
