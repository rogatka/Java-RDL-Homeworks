package io.humb1t.hometask;

public class NonDeprecatedChildClass1 extends ParentClass {
    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
