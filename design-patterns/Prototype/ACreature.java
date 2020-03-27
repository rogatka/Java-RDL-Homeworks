package Prototype;

import java.util.Objects;

public abstract class ACreature implements ICreature {
    String name; // имя существа
    ColorSet color; //цвет существа

    public ACreature(String name) {
        this.name = name;
        this.color = ColorSet.GREEN;
    }

    public ACreature(String name, ColorSet color) {
        this(name);
        this.color = color;
    }

    public ACreature(ICreature creature) {
        this(creature.getName(), creature.getColor());
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public ColorSet getColor() {
        return this.color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ACreature aCreature = (ACreature) o;
        return Objects.equals(name, aCreature.name) &&
                color == aCreature.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, color);
    }
}
