package Decorator;

public abstract class AMatrioshka {
    String name = "Matrioshka"; // имя матрёшки

    public static void main(String[] args) {
        AMatrioshka matrioshka = new BigMatrioshka(new BlueMatrioshka(new Matrioshka()));
        System.out.println(matrioshka.getName());
    }

    abstract String getName();
}

class Matrioshka extends AMatrioshka {
    String name = "Simple";

    public Matrioshka() {
        this.name = name + " " + super.name;
    }

    public Matrioshka(AMatrioshka matrioshka) {
        this.name = name + " " + matrioshka.getName();
    }

    @Override
    public String getName() {
        return name;
    }
}

class BlueMatrioshka extends AMatrioshka {
    String name = "Blue";

    public BlueMatrioshka() {
        this.name = name + " " + super.name;
    }

    public BlueMatrioshka(AMatrioshka matrioshka) {
        this.name = name + " " + matrioshka.getName();
    }

    @Override
    public String getName() {
        return name;
    }
}

class RedMatrioshka extends AMatrioshka {
    String name = "Red";

    public RedMatrioshka() {
        this.name = name + " " + super.name;
    }

    public RedMatrioshka(AMatrioshka matrioshka) {
        this.name = name + " " + matrioshka.getName();
    }

    @Override
    public String getName() {
        return name;
    }
}

class PurpleMatrioshka extends AMatrioshka {
    String name = "Purple";

    public PurpleMatrioshka() {
        this.name = name + " " + super.name;
    }

    public PurpleMatrioshka(AMatrioshka matrioshka) {
        this.name = name + " " + matrioshka.getName();
        ;
    }

    @Override
    public String getName() {
        return name;
    }
}

class BigMatrioshka extends AMatrioshka {
    String name = "Big";

    public BigMatrioshka() {
        this.name = name + " " + super.name;
    }

    public BigMatrioshka(AMatrioshka matrioshka) {
        this.name = name + " " + matrioshka.getName();
    }

    @Override
    public String getName() {
        return name;
    }
}