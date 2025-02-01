package ru.vadim.java.reactive.cource.tutorial.sec12.assingment;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.function.Consumer;

@Slf4j
@Data
public class SlackMember {

    private final String name;
    private Consumer<String> messageConsumer;

    public SlackMember(String name) {
        this.name = name;
    }

    public void says(String message) {
        this.messageConsumer.accept(message);
    }

    public void receive(String message) {
        log.info(message);
    }
}
