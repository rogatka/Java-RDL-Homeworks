package org.fundamentals.hometask.pets;

public class Cat extends Pet {
    public Cat() {
        super("Cat");
    }

    public String doAction() {
        return "catches mice";
    }

    public String makeSound() {
        return "meows";
    }
}
