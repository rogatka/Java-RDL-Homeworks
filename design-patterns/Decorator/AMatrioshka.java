package Decorator;

public abstract class AMatrioshka {
    String name; // имя матрёшки

    public static void main(String[] args) {
        AMatrioshka matrioshka = new BigMatrioshka(new BlueMatrioshka(new Matrioshka()));
        System.out.println(matrioshka.getName());
    }

    abstract String getName();
}

class Matrioshka extends AMatrioshka {
    public Matrioshka() {
        this.name = "Matrioshka";
    }

    @Override
    public String getName() {
        return name;
    }
}

class MatrioshkaDecorator extends AMatrioshka {
    protected AMatrioshka matrioshka;

    public MatrioshkaDecorator(AMatrioshka matrioshka) {
        this.matrioshka = matrioshka;
    }

    @Override
    public String getName() {
        return this.name + " " + matrioshka.getName();
    }
}

class BlueMatrioshka extends MatrioshkaDecorator {

    public BlueMatrioshka(AMatrioshka matrioshka) {
        super(matrioshka);
        this.name = "Blue";
    }
}

class RedMatrioshka extends MatrioshkaDecorator {
    public RedMatrioshka(AMatrioshka matrioshka) {
        super(matrioshka);
        this.name = "Red";
    }
}

class PurpleMatrioshka extends MatrioshkaDecorator {
    public PurpleMatrioshka(AMatrioshka matrioshka) {
        super(matrioshka);
        this.name = "Purple";
    }
}

class BigMatrioshka extends MatrioshkaDecorator {
    public BigMatrioshka(AMatrioshka matrioshka) {
        super(matrioshka);
        this.name = "Big";
    }
}