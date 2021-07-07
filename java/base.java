import java.util.Scanner;

/**
 * base
 */
public class base {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = "615";
        int b1 = 13;
        int b2 = 7;
        
        int toDeci = toDecimal(input,b1);
        System.out.println(toDeci);
    }

    private static int toDecimal(String input, int b1) {
        int store = Integer.parseInt(input);
        // int store = toInt;
        int decimal=0;
        int power = 0;

        while (store!=0) {
            int temp = store%10;
            decimal = decimal + (int)(temp*(Math.pow(b1, power)));
            power++;
            store=store/10;
        }

        
        
        return decimal;
    }
}