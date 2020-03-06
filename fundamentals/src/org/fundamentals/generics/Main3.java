package org.fundamentals.generics;

public class Main3 {
    public static void main(String[] args) {
        Language language = getLanguageByName(args[0]);
        System.out.println(language.getDescription());
        System.out.println(language.toString());
    }

    private static Language getLanguageByName(String name) {
        switch (name.toLowerCase()) {
            case "java":
                return new Language("Java", () -> "OOP", () -> "strongly-typed");
            case "js":
            case "javascript":
                return new Language("JavaScript", () -> "Multi-paradigm", () -> "dynamically-typed");
            case "python":
                return new Language("Python", () -> "Multi-paradigm", () -> "dynamically-typed");
            case "haskell":
                return new Language("Haskell",() -> "Functional", () -> "strongly-typed");
            default:
                return new Language(name, new Unknown(), new Unknown());
        }
    }
}
