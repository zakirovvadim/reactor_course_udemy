package ru.vadim.java.reactive.cource.tutorial.sec09.helper.mergeUsecase;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import ru.vadim.updatedCource.common.Util;

import java.time.Duration;

@Slf4j
public class Aeroflot {

    private static final String AIRLINE = "Aeroflot";

    public static Flux<Flight> getFlight() {
        return Flux.range(1, Util.faker().random().nextInt(2, 10))
                .delayElements(Duration.ofMillis(Util.faker().random().nextInt(200, 1000)))
                .map(i -> new Flight(AIRLINE, Util.faker().random().nextInt(5000, 15000)))
                .transform(Util.fluxLogger(AIRLINE));
    }
}
