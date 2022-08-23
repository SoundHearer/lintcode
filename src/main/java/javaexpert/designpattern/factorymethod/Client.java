package javaexpert.designpattern.factorymethod;

/**
 * @Author: SelectBook
 * @Date: 2022/8/22 21:02
 */
public class Client {
    public static void main(String[] args) {
        IFactory factory = new FactoryA();
        factory.makeProduct().doSomething();
        
        factory = new FactoryB();
        factory.makeProduct().doSomething();
        
        factory = new FactoryC();
        factory.makeProduct().doSomething();
    }
    
    // 抽象工厂
    public interface IFactory {
        IProduct makeProduct();
    }
    
    // 生产ProductA的具体工厂类
    static class FactoryA implements IFactory {
        @Override
        public IProduct makeProduct() {
            return new ProductA();
        }
    }

    // 生产ProductB的具体工厂类
    static class FactoryB implements IFactory {
        @Override
        public IProduct makeProduct() {
            return new ProductB();
        }
    }

    // 生产ProductC的具体工厂类
    static class FactoryC implements IFactory {
        @Override
        public IProduct makeProduct() {
            return new ProductC();
        }
    }
    
    // 抽象产品
    public interface IProduct {
        void doSomething();
    }
    
    // 具体产品: ProductA
    static class ProductA implements IProduct {
        @Override
        public void doSomething() {
            System.out.println("I am  Product A");
        }
    }

    // 具体产品: ProductB
    static class ProductB implements IProduct {
        @Override
        public void doSomething() {
            System.out.println("I am  Product B");
        }
    }

    // 具体产品: ProductC
    static class ProductC implements IProduct {
        @Override
        public void doSomething() {
            System.out.println("I am  Product C");
        }
    }
}
