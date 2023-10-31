import java.lang.reflect.GenericDeclaration;
import java.util.Random;

public class Test {
    public static void main(String[] args) {
        Random random = new Random();
        int randomInt = random.nextInt(1000);
        int lastDigit = Integer.parseInt(String.valueOf(randomInt).substring(String.valueOf(randomInt).length() - 1));
        System.out.println(randomInt);
        System.out.println(lastDigit);
    }
}
