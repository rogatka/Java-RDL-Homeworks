package org.fundamentals.hometask.generics;

public class Main5 {
    public static void main(String[] args) {
        printPetDescription(getPetByName(args[0]));
    }

    private static Pet getPetByName(String name) {
        switch (name.toLowerCase()) {
            case "cat":
                return new Pet("Cat", () -> "meows", () -> "catches mice");
            case "dog":
                return new Pet("Dog", () -> "barks", () -> "protects the house");
            case "chameleon":
                return new Pet("Chameleon", () -> "makes no sound", () -> "changes the color");
            case "spider":
                return new Pet("Spider",() -> "makes no sound", () -> "catches flies");
            default:
                return new Pet(name, new UnknownPet(), new UnknownPet());
        }
    }

    private static void printPetDescription(Pet pet) {
        System.out.println(pet.getDescription());
    }
}
