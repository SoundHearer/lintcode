package util;

public class CommonUtil {
    
    public static boolean isPrime(int a) {
        int j;
        for (j = 2; j < a; j++) {
            if (a%j == 0) {
                return false;
            }
        }
        return true;
    }
}
