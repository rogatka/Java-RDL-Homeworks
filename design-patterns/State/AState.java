package State;

public abstract class AState implements ICoffeeMachine {
    SimpleCoffeeMachine context;
    int price = 100;

    public AState(SimpleCoffeeMachine context) {
        this.context = context;
    }

    @Override
    public void dispatchError() {
        System.out.println("This operation is not available.");
    }
}
