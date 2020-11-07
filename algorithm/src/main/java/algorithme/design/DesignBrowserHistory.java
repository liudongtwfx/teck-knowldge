package algorithme.design;

import org.apache.commons.lang3.ClassUtils;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DesignBrowserHistory {
    public static void main(String[] args) {
        process();
    }

    private static void process() {
        Enhancer enhancer = new Enhancer();
        Object[][] args = {{"esgriv.com"}, {"cgrt.com"}, {"tip.com"}, {9}, {"kttzxgh.com"}, {7}, {"crqje.com"}, {"iybch.com"}, {5}, {"uun.com"}, {10}, {"hci.com"}, {"whula.com"}, {10}};
        String[] methods = {"BrowserHistory", "visit", "visit", "back", "visit", "forward", "visit", "visit", "forward", "visit", "back", "visit", "visit", "forward"};
        enhancer.setCallback(new BrowserHistoryMethodInterceptor());
        enhancer.setSuperclass(BrowserHistory.class);
        BrowserHistory browserHistory = (BrowserHistory) enhancer.create(new Class[]{String.class}, args[0]);
        try {
            for (int i = 1; i < methods.length; i++) {
                List<Class<?>> argsClazz = new ArrayList<>();
                for (Object o : args[i]) {
                    Class<?> clazzType = o.getClass();
                    boolean isPrimitive = ClassUtils.isPrimitiveWrapper(clazzType);
                    argsClazz.add(isPrimitive ? ClassUtils.wrapperToPrimitive(clazzType) : clazzType);
                }
                Method method = BrowserHistory.class.getMethod(methods[i], argsClazz.toArray(new Class[]{}));
                method.invoke(browserHistory, args[i]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static class BrowserHistoryMethodInterceptor implements MethodInterceptor {

        @Override
        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
            try {
                Object invoke = methodProxy.invokeSuper(o, objects);
                System.out.println(method.getName() + " : " + Arrays.toString(objects) + " , " + invoke);
                if (o instanceof BrowserHistory) {
                    System.out.println(((BrowserHistory) o).visited + " currentIndex:" + ((BrowserHistory) o).currentIndex);
                }
                return invoke;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                throw e;
            }
        }
    }

    static class BrowserHistory {
        private List<String> visited = new ArrayList<>();
        private int currentIndex;

        public BrowserHistory(String homepage) {
            visited.add(homepage);
            currentIndex = 0;
        }

        public void visit(String url) {
            if (currentIndex < visited.size() - 1) {
                this.visited = new ArrayList<>(visited.subList(0, currentIndex + 1));
            }
            visited.add(url);
            currentIndex++;
        }

        public String back(int steps) {
            currentIndex = currentIndex < steps ? 0 : currentIndex - steps;
            return visited.get(currentIndex);
        }

        public String forward(int steps) {
            int targetIndex = currentIndex + steps;
            if (targetIndex >= visited.size()) {
                currentIndex = visited.size() - 1;
                return visited.get(visited.size() - 1);
            }
            currentIndex = targetIndex;
            return visited.get(targetIndex);
        }
    }
}
