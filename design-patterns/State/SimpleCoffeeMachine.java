package State;

public class SimpleCoffeeMachine {
    private AState state;
    private int price;

    public SimpleCoffeeMachine(int price) {
        this.price = price;
        this.state = new OffState(this);
    }

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

    public void changeState(AState state) {
        System.out.println("Changing state to [" + state.getClass().getSimpleName() + "]");
        this.state = state;
    }

    public CoffeeMachineStatesSet getState() {
        return state.getState();
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void dispatchError() {
        System.out.println("This operation is not available.");
    }
}
