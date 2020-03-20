package io.humb1t.hometask;

import java.util.Objects;

@Deprecated
public class DeprecatedChildClass extends ParentClass implements ParentInterface {
    private static int counter;
    private int id;


    public DeprecatedChildClass() {
        this.id = counter++;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeprecatedChildClass that = (DeprecatedChildClass) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "DeprecatedChildClass{" +
                "id=" + id +
                '}';
    }
}
