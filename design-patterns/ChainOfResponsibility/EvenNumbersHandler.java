package ChainOfResponsibility;

public class EvenNumbersHandler extends ABaseHandler {

    @Override
    public void setNextHandler(IHandler handler) {
        this.next = handler;
    }

    @Override
    public void handle(Request request) {
        if (validate(request)) {
            System.out.println(request.getValue() + " is even number. Was handled by " + this.getClass().getSimpleName());
        }
        if (this.next != null) {
            this.next.handle(request);
        }
    }

    @Override
    public boolean validate(Request request) {
        return request.getValue() % 2 == 0;
    }
}
