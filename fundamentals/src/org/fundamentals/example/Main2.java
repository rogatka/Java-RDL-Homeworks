package org.fundamentals.example;

public class Main2 {
    public static void main(String[] args) {
        printLanguageDescription(getLanguageByName(args[0]));
    }

    private static Language getLanguageByName(String name) {
        switch (name.toLowerCase()) {
            case "java":
                return new JavaLanguage();
            case "js":
            case "javascript":
                return new JavaScriptLanguage();
            case "python":
                return new PythonLanguage();
            case "haskell":
                return new HaskellLanguage();
            default:
                return new UnknownLanguage(name);
        }
    }

    private static void printLanguageDescription(Language language) {
        System.out.println(language.getDescription());
    }
}
