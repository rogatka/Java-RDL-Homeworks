package State;

public class PreparingCoffeeState extends AState {

    public PreparingCoffeeState(SimpleCoffeeMachine context) {
        super(context);
    }

    @Override
    public void deposit(int moneyAmount) {
        dispatchError();
    }

    @Override
    public void prepareCoffee() {
        System.out.println("Preparing coffee...");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        context.changeState(new CoffeeIsReadyState(context));
    }

    @Override
    public void deliverCoffee() {
        //DO NOTHING
    }

    @Override
    public void giveChange(int moneyAmount) {
        dispatchError();
    }

    @Override
    public void init() {
        //DO NOTHING
    }

    @Override
    public void stop() {
        dispatchError();
    }

    @Override
    CoffeeMachineStatesSet getState() {
        return CoffeeMachineStatesSet.PREPARING_COFFEE;
    }
}
