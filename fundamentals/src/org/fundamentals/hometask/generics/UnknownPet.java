package org.fundamentals.hometask.generics;

public class UnknownPet implements ISound, IAction{
    @Override
    public String doAction() {
        return "does some action";
    }

    @Override
    public String makeSound() {
        return "makes some sound";
    }
}
