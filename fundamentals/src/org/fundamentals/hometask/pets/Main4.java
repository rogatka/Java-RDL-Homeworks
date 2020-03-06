package org.fundamentals.hometask.pets;

public class Main4 {
    public static void main(String[] args) {
        printPetDescription(getPetByName(args[0]));
    }

    private static Pet getPetByName(String name) {
        switch (name.toLowerCase()) {
            case "cat":
                return new Cat();
            case "dog":
                return new Dog();
            case "chameleon":
                return new Chameleon();
            case "spider":
                return new Spider();
            default:
                return new UnknownPet(name);
        }
    }

    private static void printPetDescription(Pet pet) {
        System.out.println(pet.getDescription());
    }
}
