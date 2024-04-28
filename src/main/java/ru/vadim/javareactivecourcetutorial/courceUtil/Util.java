package ru.vadim.javareactivecourcetutorial.courceUtil;

import com.github.javafaker.Faker;

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

}
