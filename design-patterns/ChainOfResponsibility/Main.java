package ChainOfResponsibility;

public class Main {
    public static void main(String[] args) {
        IHandler handler1 = new PrimeNumbersHandler();
        IHandler handler2 = new EvenNumbersHandler();
        IHandler handler3 = new DivideByThreeNumbersHandler();
        handler2.setNextHandler(handler3);
        handler1.setNextHandler(handler2);

        Request request = new Request(12);
        handler1.handle(request);
    }
}
