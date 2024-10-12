package ru.vadim.javareactivecourcetutorial.sec11.assingment;

public class ServerError extends RuntimeException {
    public ServerError() {
        super("sever error");
    }
}
