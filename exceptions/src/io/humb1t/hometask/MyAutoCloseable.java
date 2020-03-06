package io.humb1t.hometask;


import java.io.IOException;

public class MyAutoCloseable implements AutoCloseable {

    public static void main(String[] args) {
        try (MyAutoCloseable myAutoCloseable = new MyAutoCloseable()) {
            throw new IOException();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() {
        System.out.println("Closing resources...");
    }
}
