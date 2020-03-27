package State;

public class OnState extends AState {


    public OnState(SimpleCoffeeMachine context) {
        super(context);
    }

    @Override
    public void deposit(int moneyAmount) {
        if (moneyAmount < price) {
            dispatchError();
        } else {
            System.out.println("Thank you for using our SimpleCoffeeMachine! You deposit " + moneyAmount + "$");
            giveChange(moneyAmount);
            context.changeState(new PreparingCoffeeState(context));
        }
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
        if (moneyAmount > price) {
            System.out.println("Your change is " + (moneyAmount - price) + "$...");
        }
    }

    @Override
    public void init() {
        //DO NOTHING
    }

    @Override
    public void stop() {
        System.out.println("Stopping...");
        context.changeState(new OffState(context));
    }
}
