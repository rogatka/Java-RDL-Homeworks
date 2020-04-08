package homework;

import homework.Orders.Order;
import homework.Orders.OrderStatus;

public interface OrderFactory {

    default Order createCompletedOrder() {
        return new Order(OrderStatus.COMPLETED);
    }

    default Order createProcessingOrder() {
        return new Order(OrderStatus.PROCESSING);
    }

    default Order createNotStartedOrder() {
        return new Order(OrderStatus.NOT_STARTED);
    }
}
