package Prototype;

public class Humanoid extends ACreature {
    private final static String ALPHABET = "abcdefghijklmnopqrstuvwxyz";

    public Humanoid(String name) {
        super(name);
    }

    public Humanoid(String name, ColorSet color) {
        super(name, color);
    }

    public Humanoid(ICreature creature) {
        super(creature);
    }

    @Override
    public Humanoid copy() {
        Humanoid myClone = new Humanoid(this);
        myClone.name = generateName();
        return myClone;
    }

    private static String generateName() {
        StringBuilder name = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            name.append(ALPHABET.charAt((int)(Math.random() * (ALPHABET.length()-1))));
        }
        return name.toString();
    }

    @Override
    public String toString() {
        return "MyCreature{" +
                "name='" + name + '\'' +
                ", color=" + color +
                '}';
    }
}
