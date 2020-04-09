package ChainOfResponsibility;

public class ErrorsHandler extends ABaseHandler {

    @Override
    public void setNextHandler(IHandler handler) {
        throw new UnsupportedOperationException("Error handler must be the last handler");
    }

    @Override
    public void handle(Request request) {
        System.out.println(request.getValue() + " was handled by " + this.getClass().getSimpleName());
    }

    @Override
    public boolean validate(Request request) {
        return true;
    }
}
