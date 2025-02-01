package ru.vadim.java.reactive.cource.tutorial.courceUtil;

import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

public class NameGenerator {

    public static List<String> getNames(int count) {
        ArrayList<String> list = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            list.add(getName());
        }
        return list;
    }

    public static Flux<String> getFluxNames(int count) {
        return Flux.range(0, count)
                .map(i -> getName());
    }

    private static String getName() {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return Util.faker().name().fullName();
    }
}
