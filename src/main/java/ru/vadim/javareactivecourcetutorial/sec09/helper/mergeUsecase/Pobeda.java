package ru.vadim.javareactivecourcetutorial.sec09.helper.mergeUsecase;

import reactor.core.publisher.Flux;
import ru.vadim.updatedCource.common.Util;

import java.time.Duration;

public class Pobeda {

    private static final String AIRLINE ="Pobeda";

    public static Flux<Flight> getFlight() {
        return Flux.range(1, Util.faker().random().nextInt(2, 10))
                .delayElements(Duration.ofMillis(Util.faker().random().nextInt(200, 1000)))
                .map(i -> new Flight(AIRLINE, Util.faker().random().nextInt(2500, 10000)))
                .transform(Util.fluxLogger(AIRLINE));
    }
}
