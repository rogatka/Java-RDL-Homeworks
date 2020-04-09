package State;

public interface ICoffeeMachine {
    void deposit(int moneyAmount); // принять деньги
    void prepareCoffee(); // приготовить кофе
    void deliverCoffee(); // выдать кофе клиенту
    void giveChange(int moneyAmount); // отдать сдачу

    void dispatchError(); // обработчик ошибок (например написать сообщение об ошибке клиенту)

    void init(); // инициализация
    void stop(); // остановить и больше не принимать команды
}
