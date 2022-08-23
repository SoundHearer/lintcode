package javaexpert.designpattern.factory;

/**
 * @Author: SelectBook
 * @Date: 2022/8/22 9:26
 */
public class Client {
     public static void main(String[] args) {
         
     }
     
     // 抽象产品
    public interface IProduct {
         void doSomething();
     }
     
     // 具体产品，ProductA
    static class ProductA implements IProduct {
         @Override
         public void doSomething() {
             System.out.println("I am Product A");
         }
     }

    // 具体产品，ProductB
    static class ProductB implements IProduct {
        @Override
        public void doSomething() {
            System.out.println("I am Product B");
        }
    }

    // 具体产品，ProductC
    static class ProductC implements IProduct {
        @Override
        public void doSomething() {
            System.out.println("I am Product C");
        }
    }

    final class Const {
         static final int PRODUCT_A = 0;
         static final int PRODUCT_B = 1;
         static final int PRODUCT_C = 2;
    }
    
    static class SimpleFactory {
         public static IProduct makeProduct(int kind) {
             switch (kind) {
                 case Const.PRODUCT_A:
                     return new ProductA(); 
                 case Const.PRODUCT_B:
                     return new ProductB(); 
                 case Const.PRODUCT_C:
                     return new ProductC();
             }
             return null;
         }
    }

}
