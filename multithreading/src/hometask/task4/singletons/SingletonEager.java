package hometask.task4.singletons;

public class SingletonEager {
    public static final SingletonEager INSTANCE = new SingletonEager();

    private SingletonEager() {
        System.out.println("Singleton was created");
    }
}
