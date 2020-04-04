package ChainOfResponsibility;

public class Main {
    public static void main(String[] args) {
        IHandler handler1 = new PrimeNumbersHandler();
        IHandler handler2 = new EvenNumbersHandler();
        IHandler handler3 = new DivideByThreeNumbersHandler();
        IHandler handler4 = new ErrorsHandler();
        handler3.setNextHandler(handler4);
        handler2.setNextHandler(handler3);
        handler1.setNextHandler(handler2);

        Request request1 = new Request(7);
        handler1.handle(request1);
        Request request2 = new Request(8);
        handler1.handle(request2);
        Request request3 = new Request(15);
        handler1.handle(request3);
        Request request4 = new Request(25);
        handler1.handle(request4);
    }
}
