package org.fundamentals.hometask.generics;

public class Pet<A extends IAction, S extends ISound> {
    private String name;
    private S sound;
    private A action;

    public Pet(String name, S sound, A action) {
        this.name = name;
        this.sound = sound;
        this.action = action;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.name + " " + this.sound.makeSound() + " and " + this.action.doAction() + ".";
    }
}
