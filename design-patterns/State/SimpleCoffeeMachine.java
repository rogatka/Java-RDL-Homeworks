package State;

public class SimpleCoffeeMachine {
    private ICoffeeMachine state = new OffState(this);

    public void init() {
        state.init();
    }

    public void makeCoffee(int moneyAmount) {
        state.deposit(moneyAmount);
        state.prepareCoffee();
        state.deliverCoffee();
    }

    public void stop() {
        state.stop();
    }

    public void changeState(ICoffeeMachine state) {
        System.out.println("Changing state to [" + state.getClass().getSimpleName() + "]");
        this.state = state;
    }
}
