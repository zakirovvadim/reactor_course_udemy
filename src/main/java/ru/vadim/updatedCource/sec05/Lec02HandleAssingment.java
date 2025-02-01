package ru.vadim.updatedCource.sec05;

import com.github.javafaker.Faker;
import reactor.core.publisher.Flux;
import ru.vadim.java.reactive.cource.tutorial.courceUtil.Util;

public class Lec02HandleAssingment {
    public static void main(String[] args) {
        Flux<String> generateCountry = Flux.<String>generate(sink -> {
            String name = Faker.instance().country().name();
            sink.next(name);
        });

        generateCountry
                .cast(String.class)
                .handle((item, sink) -> {
                    switch (item) {
                        case "Canada" -> sink.complete();
                        case "Russian Federation" -> sink.next(item + " failed the war with Ukraine");
//                        case "Mongolia" -> sink.error(new RuntimeException("MONGOLIA"));
                        default -> sink.next(item);
                    }
                }).subscribe(Util.subscriber());
    }
}
