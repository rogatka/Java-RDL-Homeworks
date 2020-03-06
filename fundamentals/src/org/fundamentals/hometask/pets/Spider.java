package org.fundamentals.hometask.pets;

public class Spider extends Pet {
    public Spider() {
        super("Spider");
    }

    public String doAction() {
        return "catches flies";
    }

    public String makeSound() {
        return "makes no sound";
    }
}
