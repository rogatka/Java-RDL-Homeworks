package State;

public class OffState extends AState {

    public OffState(SimpleCoffeeMachine context) {
        super(context);
    }

    @Override
    public void deposit(int moneyAmount) {
        //DO NOTHING
    }

    @Override
    public void prepareCoffee() {
        //DO NOTHING
    }

    @Override
    public void deliverCoffee() {
        //DO NOTHING
    }

    @Override
    public void giveChange(int moneyAmount) {
        //DO NOTHING
    }

    @Override
    public void dispatchError() {
        //DO NOTHING
    }

    @Override
    public void init() {
        System.out.println("Initialization...");
        context.changeState(new OnState(context));
    }

    @Override
    public void stop() {
        //DO NOTHING
    }

    @Override
    CoffeeMachineStatesSet getState() {
        return CoffeeMachineStatesSet.OFF;
    }
}
