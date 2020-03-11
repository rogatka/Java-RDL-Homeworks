package homework;


import java.util.*;

public class Orders {
    static List<Order> orderList = getFilledOrdersList();

    public static void main(String[] args) {
        Collections.shuffle(orderList);

        orderList.stream().filter(order -> order.isOrderNumberOfItemsMoreThan(30)).forEach(System.out::println);
        orderList.stream().filter(order -> order.isOrderIdMoreThan(50)).forEach(System.out::println);
    }

    public static List<Order> getFilledOrdersList() {
        List<Order> listOfOrders = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            listOfOrders.add(new Order(OrderStatus.NOT_STARTED, new Random().nextInt(100) + 1));
        }
        for (int i = 20; i < 40; i++) {
            listOfOrders.add(new Order(OrderStatus.PROCESSING, new Random().nextInt(100) + 1));
        }
        for (int i = 40; i < 60; i++) {
            listOfOrders.add(new Order(OrderStatus.COMPLETED, new Random().nextInt(100) + 1));
        }
        return listOfOrders;
    }

    public enum OrderStatus {
        NOT_STARTED, PROCESSING, COMPLETED
    }

    public static class Order {
        public final OrderStatus status;
        private static long id;
        private final long orderId;
        private int numberOfItems;

        public Order(OrderStatus status, int countOfItems) {
            this(status);
            this.numberOfItems = countOfItems;
        }

        public Order(OrderStatus status) {
            this.status = status;
            this.orderId = ++id;
        }

        public OrderStatus getStatus() {
            return status;
        }

        public long getOrderId() {
            return orderId;
        }

        public int getNumberOfItems() {
            return numberOfItems;
        }

        public boolean isOrderNumberOfItemsMoreThan (int numberOfItems) {
            return this.numberOfItems > numberOfItems;
        }
        public boolean isOrderIdMoreThan (long id) {
            return this.orderId > id;
        }

        @Override
        public String toString() {
            return "Order{id = " + orderId + ", number of items = " + numberOfItems + "}";
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Order order = (Order) o;
            return orderId == order.orderId &&
                    numberOfItems == order.numberOfItems &&
                    status == order.status;
        }

        @Override
        public int hashCode() {
            return Objects.hash(status, orderId, numberOfItems);
        }
    }
}
