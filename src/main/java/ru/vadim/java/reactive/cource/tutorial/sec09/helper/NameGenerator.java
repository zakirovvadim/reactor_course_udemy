package ru.vadim.java.reactive.cource.tutorial.sec09.helper;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import ru.vadim.java.reactive.cource.tutorial.courceUtil.Util;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class NameGenerator {
    List<String> redis = new ArrayList<>();
    public Flux<String> generateName() {
        return Flux.generate(synchronousSink -> {
            log.info("Start generating name");
            Util.sleepSeconds(1);
            String name = Util.faker().name().firstName();
            redis.add(name);
            synchronousSink.next(name);
        })
                .startWith(redis)
                .cast(String.class);
    }
}
