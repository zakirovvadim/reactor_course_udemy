package ru.vadim.java.reactive.cource.tutorial.sec12.assingment;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public record SlackMessage (String sender, String message) {

    public static final String MESSAGE_FORMAT = "[%s -> %s]  : %s";

    public String formatDelivery(String receiver) {
        return MESSAGE_FORMAT.formatted(sender, receiver, message);
    }
}
