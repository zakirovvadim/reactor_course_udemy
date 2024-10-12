package ru.vadim.javareactivecourcetutorial.sec11.assingment;

public class ClientError extends RuntimeException{
    public ClientError() {
        super("bad request");
    }
}
