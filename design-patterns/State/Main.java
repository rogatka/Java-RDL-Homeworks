package State;

public class Main {
    final static int PRICE = 100;

    public static void main(String[] args) {
        SimpleCoffeeMachine coffeeMachine = new SimpleCoffeeMachine(PRICE);
        coffeeMachine.init();
        System.out.println("---------------------------");
        coffeeMachine.makeCoffee(120);
        System.out.println("---------------------------");
        coffeeMachine.makeCoffee(80);
        System.out.println(coffeeMachine.getState());
        System.out.println("---------------------------");
        coffeeMachine.stop();
    }
}
