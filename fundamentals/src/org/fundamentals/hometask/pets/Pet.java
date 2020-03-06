package org.fundamentals.hometask.pets;

public abstract class Pet implements IAction, ISound {
    private String name;

    protected Pet(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.name + " " + this.makeSound() + " and " + this.doAction() + ".";
    }
}
