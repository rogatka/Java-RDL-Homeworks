package io.humb1t.hometask.task2and4;


import java.util.*;
import java.util.stream.Collectors;

public class Orders {
    static List<Order> orderList = getFilledOrdersList();
    static List<Integer> listOfNums = getFilledNumberList();

    public static void main(String[] args) {
        Collections.shuffle(orderList);

        printFilteredByStatus(OrderStatus.COMPLETED);

        System.out.println("\r\n-------------------------------------------");
        printFilteredByNumberOfItems(5);

        System.out.println("\r\n-------------------------------------------");
        printFilteredById(50);

        System.out.println("\r\n-------------------------------------------");

        System.out.println("List with duplicates:\r\n" + listOfNums);
        System.out.println("List without duplicates (using stream):\r\n" + getListWithoutDuplicatesUsingStream());
        System.out.println("List with duplicates:\r\n" + listOfNums);
        System.out.println("Set without duplicates:\r\n" + getSetWithoutDuplicates(listOfNums));
    }

    private static List<Integer> getListWithoutDuplicatesUsingStream() {
        return listOfNums
                .stream()
                .distinct()
                .collect(Collectors.toList());
    }

    private static Set<Integer> getSetWithoutDuplicates(List<Integer> list) {
        return new HashSet<>(list);
    }

    private static List<Integer> getFilledNumberList() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(i % 10);
        }
        return list;
    }

    private static void printFilteredById(int id) {
        System.out.println("Orders with ids > " + id + ":");
        List<Order> sortedList = orderList.stream()
                .filter(order -> order.getOrderId() > id)
                .sorted(Comparator.comparingLong(Order::getOrderId))
                .collect(Collectors.toList());
        sortedList.forEach(System.out::println);
    }

    private static void printFilteredByNumberOfItems(int numberOfItems) {
        System.out.println("Orders grouping by number of items(>" + numberOfItems + ") in order:");
        Map<Integer, List<Order>> groupByNumberOfItems = orderList.stream()
                .filter(order -> order.getNumberOfItems() > numberOfItems)
                .collect(Collectors.groupingBy(Order::getNumberOfItems));

        groupByNumberOfItems.entrySet()
                .stream()
                .sorted(Comparator.comparingInt(Map.Entry::getKey))
                .forEach((entry) ->
                        System.out.println("Number of items in order: " + entry.getKey() +
                                "\r\n Orders: " + entry.getValue().stream().map(order -> "id = " + order.getOrderId()).collect(Collectors.toList())));
    }

    private static void printFilteredByStatus(OrderStatus status) {
        System.out.println(status.name() + " orders:");
        orderList.stream()
                .filter(order -> order.getStatus() == status)
                .sorted(Comparator.comparingLong(Order::getOrderId))
                .forEach(System.out::println);
    }

    private static List<Order> getFilledOrdersList() {
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
