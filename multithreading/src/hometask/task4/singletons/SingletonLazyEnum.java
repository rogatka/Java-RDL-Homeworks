package hometask.task4.singletons;

public enum SingletonLazyEnum {
    INSTANCE;

    private SingletonLazyEnum() {
        System.out.println("Lazy Singleton was created using Enum");
    }
}
