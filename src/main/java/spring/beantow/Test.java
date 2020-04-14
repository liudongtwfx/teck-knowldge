package spring.beantow;

import org.springframework.stereotype.Component;

import java.util.Scanner;

/**
 * @author liudong
 */
@Component
public class Test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int i = scanner.nextInt();
            System.out.println(i);
            System.out.println(scanner.toString());
        }
    }
}
