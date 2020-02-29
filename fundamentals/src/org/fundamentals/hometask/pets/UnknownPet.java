package org.fundamentals.hometask.pets;

public class UnknownPet extends Pet {
    public UnknownPet(String name) {
        super(name);
    }

    public String doAction() {
        return "does some action";
    }

    public String makeSound() {
        return "makes some sound";
    }
}
