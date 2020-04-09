package hometask.task4;

import hometask.task4.singletons.SingletonEager;
import hometask.task4.singletons.SingletonLazy;
import hometask.task4.singletons.SingletonLazyEnum;

public class Main {
    public static void main(String[] args) {
        SingletonEager singletonEager1 = SingletonEager.INSTANCE;
        SingletonEager singletonEager2 = SingletonEager.INSTANCE;
        SingletonLazy singletonLazy1 = SingletonLazy.getInstance();
        SingletonLazy singletonLazy2 = SingletonLazy.getInstance();
        SingletonLazyEnum singletonLazyEnum1 = SingletonLazyEnum.INSTANCE;
        SingletonLazyEnum singletonLazyEnum2 = SingletonLazyEnum.INSTANCE;
    }
}
