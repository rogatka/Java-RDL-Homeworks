package State;

public class CoffeeIsReadyState extends AState {

    public CoffeeIsReadyState(SimpleCoffeeMachine context) {
        super(context);
    }

    @Override
    public void deposit(int moneyAmount) {
        dispatchError();
    }

    @Override
    public void prepareCoffee() {
        dispatchError();
    }

    @Override
    public void deliverCoffee() {
        System.out.println("Your coffee is ready! Have a good day!");
        context.changeState(new OnState(context));
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
}
