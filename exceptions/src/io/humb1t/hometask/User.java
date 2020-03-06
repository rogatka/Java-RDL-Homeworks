package io.humb1t.hometask;

public class User {
    static User user;

    private String name;
    private int age;

    public User(String name, int age) throws IncorrectAgeException {
        user = this;
        this.name = name;
        if (age < 0 ) {
            throw new IncorrectAgeException();
        }
        this.age = age;
    }

    //If we will try to create user with incorrect input parameters, object will not be fully constructed,
    // so it will not be associated with the reference.A reference to the new object is stored somewhere else
    // before an exception will be thrown
    public static void main(String[] args) {
        try {
            user = new User("Adam", -20);
            System.out.printf("User %s was successfully created", user);
        } catch (IncorrectAgeException e) {
            System.out.printf("Unsuccessful creation of user: %s", user);
        }
    }

    @Override
    public String toString() {
        return name + "{age=" + age + '}';
    }

    public static class IncorrectAgeException extends Exception {
        public IncorrectAgeException() {
        }

        public IncorrectAgeException(String message) {
            super(message);
        }

        public IncorrectAgeException(String message, Throwable cause) {
            super(message, cause);
        }

        public IncorrectAgeException(Throwable cause) {
            super(cause);
        }
    }
}
