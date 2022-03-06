package designpattern.stratege;

public class MiniDuckSimulator {
    public static void main(String[] args) {
        Duck mallard = new MallardDuck();
        mallard.performQuack();
        mallard.performFly();

        Duck model = new ModelDuck();
        model.performFly();
        // 要在运行时更改鸭子的行为，只需为该行为调用鸭子的 setter 方法。
        model.setFlyBehavior(new FlyRocketPowered());
        model.performFly();
    }
}
