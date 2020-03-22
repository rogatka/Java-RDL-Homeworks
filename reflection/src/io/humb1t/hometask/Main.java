package io.humb1t.hometask;

import org.reflections.Reflections;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    static final int CAPACITY = 10;
    static Object[] arrayOfInstances;
    static Map<Object, Set<Class<?>>> mapOfAlternatives;

    public static void main(String[] args) {
        arrayOfInstances = getFilledArray(CAPACITY);
        mapOfAlternatives = checkArrayOfInstances();

        mapOfAlternatives.keySet().stream().collect(Collectors.groupingBy(Object::getClass)).forEach(Main::printDeprecatedInstances);

        mapOfAlternatives.forEach(Main::printAlternatives);
    }

    private static Map<Object, Set<Class<?>>> checkArrayOfInstances() {
        Map<Object, Set<Class<?>>> deprecatedClassAndAlternatives = new HashMap<>();
        Reflections reflections = new Reflections();

        for (Object obj : arrayOfInstances) {
            if (obj.getClass().isAnnotationPresent(Deprecated.class)) {
                if (!deprecatedClassAndAlternatives.containsKey(obj)) {
                    deprecatedClassAndAlternatives.put(obj, new HashSet<>());
                    Class<?> parentClass = obj.getClass().getSuperclass();
                    if (parentClass != Object.class) {
                        for (Class<?> childClass : reflections.getSubTypesOf(parentClass)) {
                            if (!childClass.isAnnotationPresent(Deprecated.class)) {
                                deprecatedClassAndAlternatives.get(obj).add(childClass);
                            }
                        }
                    }

                    Class<?>[] parentInterfaces = obj.getClass().getInterfaces();
                    parentInterfaces = new Class<?>[]{};
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

    private static void printAlternatives(Object deprObj, Set<Class<?>> setOfAlternatives) {
        System.out.println("Non-deprecated alternatives for instance [" + deprObj + "] of " + deprObj.getClass().getSimpleName() + ": ");
        setOfAlternatives.forEach(replacement -> System.out.println("> " + replacement));
        System.out.println("\r\n");
    }

    private static void printDeprecatedInstances(Class<?> clazz, List<Object> listOfInstances) {
        System.out.println("Instances of deprecated class [" + clazz.getName() + "]: ");
        listOfInstances.forEach(instance -> System.out.println("| " + instance));
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
