package homework;

import homework.Orders.Order;
import homework.Orders.OrderStatus;

public interface OrderFactory {

    default Order getCompletedOrder() {
        return new Order(OrderStatus.COMPLETED);
    }

    default Order getProcessingOrder() {
        return new Order(OrderStatus.PROCESSING);
    }

    default Order getNotStartedOrder() {
        return new Order(OrderStatus.NOT_STARTED);
    }
}
