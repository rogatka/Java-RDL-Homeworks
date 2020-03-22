package io.humb1t;

import java.lang.reflect.*;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException {
        Class c = new Order().getClass();
        Class os = OrderStatus.PROCESSING.getClass();
        Class primitiveClass = boolean.class;
        Class orderClassByName = Class.forName("io.humb1t.Main$Order");
//        Class arrayClassByStrangeName = Class.forName("[L.io.humb1t.Main.Order;");
        Class voidClass = Void.TYPE;

        final Class<Processor> processorClass = Processor.class;
        System.out.println("Canonical name: " + processorClass.getCanonicalName());
        System.out.println("Modifiers: " + Modifier.toString(processorClass.getModifiers()));
        System.out.println("Type parameters:");
        for (TypeVariable<Class<Processor>> typeParameter : processorClass.getTypeParameters()) {
            System.out.println(typeParameter);
        }

        try {
            Class mainClass = Main.class;
            Method mainMethod = mainClass.getMethod("main", String[].class);
        } catch (NoSuchMethodException x) {
            x.printStackTrace();
        }

        try {
            Class<?> mainClass = Main.class;
            Method mainMethod = mainClass.getMethod("main", String[].class);
        } catch (NoSuchMethodException x) {
            x.printStackTrace();
        }

        Class<?> mainClass = Class.forName("io.humb1t.Main");
        try {
            Main mainObj = (Main) mainClass.newInstance();
            Class<?> innerClass = Class.forName("io.humb1t.Main$ClassWithPrivateNoArgsConstructor");
            Constructor<?> innerClassConstructor = innerClass.getDeclaredConstructor(mainClass);
            innerClassConstructor.setAccessible(true);
            ClassWithPrivateNoArgsConstructor innerObj = (ClassWithPrivateNoArgsConstructor) innerClassConstructor.newInstance(mainObj);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public enum OrderStatus {
        PROCESSING
    }

    public static class Order {

    }

    protected class Processor<E> {
        E processingElement;
    }

    public class ClassWithPrivateNoArgsConstructor {
        private ClassWithPrivateNoArgsConstructor(){
        }
    }
}
