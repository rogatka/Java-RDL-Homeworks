package ChainOfResponsibility;

import java.math.BigInteger;

public class PrimeNumbersHandler extends ABaseHandler {

    @Override
    public void setNextHandler(IHandler handler) {
        this.next = handler;
    }

    @Override
    public void handle(Request request) {
        if (validate(request)) {
            System.out.println(request.getValue() + " is prime number. Was handled by " + this.getClass().getSimpleName());
        } else {
            if (this.next != null) {
                this.next.handle(request);
            }
        }
    }

    @Override
    public boolean validate(Request request) {
        return new BigInteger(String.valueOf(request.getValue())).isProbablePrime(100);
    }
}
