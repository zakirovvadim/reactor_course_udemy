package ru.vadim.java.reactive.cource.tutorial.sec11.assingment;

public class ClientError extends RuntimeException{
    public ClientError() {
        super("bad request");
    }
}
