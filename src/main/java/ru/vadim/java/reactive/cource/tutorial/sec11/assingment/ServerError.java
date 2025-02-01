package ru.vadim.java.reactive.cource.tutorial.sec11.assingment;

public class ServerError extends RuntimeException {
    public ServerError() {
        super("sever error");
    }
}
