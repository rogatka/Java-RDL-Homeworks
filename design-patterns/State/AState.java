package State;

public abstract class AState {
    SimpleCoffeeMachine context;

    public AState(SimpleCoffeeMachine context) {
        this.context = context;
    }
    public void dispatchError() {
        System.out.println("This operation is not available.");
    }

    abstract void deposit(int moneyAmount);
    abstract void prepareCoffee();
    abstract void deliverCoffee();
    abstract void giveChange(int moneyAmount);
    abstract void init();
    abstract void stop();

    abstract CoffeeMachineStatesSet getState();
}
