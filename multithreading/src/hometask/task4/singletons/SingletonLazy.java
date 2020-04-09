package hometask.task4.singletons;

public class SingletonLazy {
    private static volatile SingletonLazy instance;

    private SingletonLazy(){
        System.out.println("Lazy Singleton was created");
    }

    public static SingletonLazy getInstance() {
        if (instance == null) {
            synchronized (SingletonLazy.class) {
                if (instance == null) {
                    instance = new SingletonLazy();
                }
            }
        }
        return instance;
    }
}
