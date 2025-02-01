package ru.vadim.java.reactive.cource.tutorial.courceUtil;

import com.github.javafaker.Faker;

import java.time.Duration;
import java.util.function.Consumer;

public class Util {

    private static final Faker FAKER = Faker.instance();

    public static Consumer<Object> onNext() {
        return o -> System.out.println("Received : " + o);
    }

    public static Consumer<Object> onError() {
        return e -> System.out.println("ERROR : " + e);
    }

    public static Runnable onComplete() {
        return () -> System.out.println("Complete");
    }

    public static Faker faker() {
        return FAKER;
    }

    public static DefaultSubscriber subscriber(String name) {
        return new DefaultSubscriber(name);
    }

    public static DefaultSubscriber subscriber() {
        return new DefaultSubscriber();
    }

    public static void sleepSeconds(long seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void sleep(Duration duration) {
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
