package State;

public class Main {
    public static void main(String[] args) {
        SimpleCoffeeMachine coffeeMachine = new SimpleCoffeeMachine();
        coffeeMachine.init();
        System.out.println("---------------------------");
        coffeeMachine.makeCoffee(120);
        System.out.println("---------------------------");
        coffeeMachine.makeCoffee(80);
        System.out.println("---------------------------");
        coffeeMachine.stop();
    }
}
