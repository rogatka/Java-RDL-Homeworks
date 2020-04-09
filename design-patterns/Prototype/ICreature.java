package Prototype;

public interface ICreature {
    String getName(); // имя
    ColorSet getColor(); // цвет
    ICreature copy(); // сделать клона
}
