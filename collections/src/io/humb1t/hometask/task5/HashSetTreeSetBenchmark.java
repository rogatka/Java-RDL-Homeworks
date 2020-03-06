package io.humb1t.hometask.task5;

import java.util.*;

public class HashSetTreeSetBenchmark {
    public static final int COUNTER = 1_000_000;

    public static void main(String[] args) {
        Set<Element> hashSet = new HashSet<>();
        Set<Element> treeSet = new TreeSet<>();

        adding1000000(hashSet);
        adding1000000(treeSet);
        getting1000000(hashSet);
        getting1000000(treeSet);
        removing1000000(hashSet);
        removing1000000(treeSet);
    }

    private static void adding1000000(Set<Element> set) {
        Random random = new Random(1);
        long start = System.currentTimeMillis();
        for (int i = 0; i < COUNTER; i++) {
            set.add(new Element("" + random.nextInt(Integer.MAX_VALUE)));
        }
        long end = System.currentTimeMillis();
        System.out.println("Adding " + COUNTER + " elements to the " + set.getClass().getSimpleName() + " takes " + (end - start) + " ms");
    }

    private static void getting1000000(Set<Element> set) {
        Element e = new Element("-1");
        long start = System.currentTimeMillis();
        for (int i = 0; i < COUNTER; i++) {
            set.contains(e);
        }
        long end = System.currentTimeMillis();
        System.out.println("Getting an element that doesn't contain in the " + set.getClass().getSimpleName() + " " + COUNTER + " times takes " + (end - start) + " ms");
    }

    private static void removing1000000(Set<Element> set) {
        Random random = new Random(1);
        long start = System.currentTimeMillis();
        for (int i = 0; i < COUNTER; i++) {
            set.remove(new Element("" + random.nextInt(Integer.MAX_VALUE)));
        }
        long end = System.currentTimeMillis();
        System.out.println("Removing all elements from the " + set.getClass().getSimpleName() + " takes " + (end - start) + " ms");
    }

    public static class Element implements Comparable<Element> {
        private String name;

        public Element(String name) {
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Element element = (Element) o;
            return Objects.equals(name, element.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
//            return 1;
        }

        @Override
        public int compareTo(Element o) {
            return this.name.compareTo(o.name);
        }
    }
}
