package ChainOfResponsibility;

public class DivideByThreeNumbersHandler extends ABaseHandler {

    @Override
    public void setNextHandler(IHandler handler) {
        this.next = handler;
    }

    @Override
    public void handle(Request request) {
        if (validate(request)) {
            System.out.println(request.getValue() + " is divided by 3. Was handled by " + this.getClass().getSimpleName());
        } else {
            if (this.next != null) {
                this.next.handle(request);
            }
        }
    }

    @Override
    public boolean validate(Request request) {
        return request.getValue() % 3 == 0;
    }
}
