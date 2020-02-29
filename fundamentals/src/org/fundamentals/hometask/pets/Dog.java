package org.fundamentals.hometask.pets;

public class Dog extends Pet {
    public Dog() {
        super("Dog");
    }

    public String doAction() {
        return "protects the house";
    }

    public String makeSound() {
        return "barks";
    }
}
