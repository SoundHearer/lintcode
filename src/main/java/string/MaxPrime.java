package string;

import util.CommonUtil;

public class MaxPrime {
    
    public void maxPrime() {
        int s = 50;
        for (int i = s / 2; i >= 2; i--) {
            if (CommonUtil.isPrime(i)) {
                if (CommonUtil.isPrime(s - i)) {
                    System.out.print(i + "*" + (s - i) + "=" + (i * (s - i)));
                    break;
                }
            }
        }
    }
    
    public static void main(String[] args) {
        new MaxPrime().maxPrime();
    }
}
