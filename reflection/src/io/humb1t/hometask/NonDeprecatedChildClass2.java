package io.humb1t.hometask;

public class NonDeprecatedChildClass2 implements ParentInterface {
    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
