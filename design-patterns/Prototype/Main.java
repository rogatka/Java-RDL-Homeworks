package Prototype;

public class Main {
    public static void main(String[] args) {

        ICreature adam = new Humanoid("Adam");
        MyPlanet planet = new MyPlanet();

        System.out.println("Population of humanoids before invasion:" + planet.getPopulation());
        for (int i = 0; i < 20; i++) {
            planet.addCreature(adam);
        }
        System.out.println("Population of humanoids after invasion: " + planet.getPopulation());
        planet.creatures.forEach(System.out::println);
    }
}
