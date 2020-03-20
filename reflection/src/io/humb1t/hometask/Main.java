package io.humb1t.hometask;

import org.reflections.Reflections;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Main {
    static final int CAPACITY = 10;
    static Object[] arrayOfInstances;
    static Map<Object, Set<Class<?>>> mapOfAlternatives;

    public static void main(String[] args) {
        arrayOfInstances = getFilledArray(CAPACITY);

        mapOfAlternatives = checkArrayOfInstances();

        mapOfAlternatives.forEach(Main::printFormattedOutput);
    }

    private static Map<Object, Set<Class<?>>> checkArrayOfInstances() {
        Map<Object, Set<Class<?>>> deprecatedClassAndAlternatives = new HashMap<>();
        Reflections reflections = new Reflections();

        for (Object obj : arrayOfInstances) {
            if (obj.getClass().isAnnotationPresent(Deprecated.class)) {
                if (!deprecatedClassAndAlternatives.containsKey(obj)) {
                    deprecatedClassAndAlternatives.put(obj, new HashSet<>());
                    Class<?> parentClass = obj.getClass().getSuperclass();
                    for (Class<?> childClass : reflections.getSubTypesOf(parentClass)) {
                        if (!childClass.isAnnotationPresent(Deprecated.class)) {
                            deprecatedClassAndAlternatives.get(obj).add(childClass);
                        }
                    }

                    Class<?>[] parentInterfaces = obj.getClass().getInterfaces();
                    for (Class<?> parentInterface : parentInterfaces) {
                        for (Class<?> inheritClass : reflections.getSubTypesOf(parentInterface)) {
                            if (!inheritClass.isAnnotationPresent(Deprecated.class)) {
                                deprecatedClassAndAlternatives.get(obj).add(inheritClass);
                            }
                        }
                    }
                }
            }
        }
        return deprecatedClassAndAlternatives;
    }

    private static void printFormattedOutput(Object deprObj, Set<Class<?>> setOfAlternatives) {
        System.out.println("Non-deprecated alternatives for instance [" + deprObj + "] of " + deprObj.getClass().getSimpleName() + ": ");
        setOfAlternatives.forEach(repl -> System.out.println("> " + repl));
        System.out.println("\r\n");
    }

    private static Object[] getFilledArray(int capacity) {
        Object[] arrayOfClasses = new Object[capacity];
        SomeClasses[] someClasses = SomeClasses.values();
        for (int i = 0; i < arrayOfClasses.length; i++) {
            try {
                arrayOfClasses[i] = createInstance(someClasses[i % someClasses.length]);
            } catch (IllegalAccessException | InstantiationException e) {
                e.printStackTrace();
            }
        }
        return arrayOfClasses;
    }

    static Object createInstance(SomeClasses clazz) throws IllegalAccessException, InstantiationException {
        switch (clazz) {
            case DEPRECATED:
                return DeprecatedChildClass.class.newInstance();
            case NON_DEPR_EXTENDS_CLASS:
                return NonDeprecatedChildClass1.class.newInstance();
            case NON_DEPR_IMPLEMENT_INTERFACE:
                return NonDeprecatedChildClass2.class.newInstance();
            default:
                return new Object();
        }
    }
}
