package Prototype;

import java.util.ArrayList;

public class MyPlanet extends APlanet {
    private long population;

    public MyPlanet() {
        creatures = new ArrayList<>();
    }

    @Override
    public void addCreature(ICreature creature) {
        creatures.add(creature.copy());
        population++;
    }

    @Override
    public long getPopulation() {
        return population;
    }
}
