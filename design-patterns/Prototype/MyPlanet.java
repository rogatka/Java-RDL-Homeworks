package Prototype;

import java.util.ArrayList;

public class MyPlanet extends APlanet {
    private long population;

    public MyPlanet() {
        creatures = new ArrayList<>();
    }

    @Override
    public void addCreature(ICreature creature) {
        ICreature clone = creature.copy();
        while (creatures.contains(clone)) {
            clone = creature.copy();
        }
        creatures.add(clone);
        population++;
    }

    @Override
    public long getPopulation() {
        return population;
    }
}
