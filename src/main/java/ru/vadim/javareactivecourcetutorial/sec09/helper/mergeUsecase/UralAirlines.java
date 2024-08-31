package ru.vadim.javareactivecourcetutorial.sec09.helper.mergeUsecase;

import reactor.core.publisher.Flux;
import ru.vadim.updatedCource.common.Util;

import java.time.Duration;

public class UralAirlines {
    private static final String AIRLINE = "Ural Airlines";

    public static Flux<Flight> getFlight() {
        return Flux.range(1, Util.faker().random().nextInt(5, 9))
                .delayElements(Duration.ofMillis(Util.faker().random().nextInt(100, 900)))
                .map(i -> new Flight(AIRLINE, Util.faker().random().nextInt(4500, 12000)))
                .transform(Util.fluxLogger(AIRLINE));
    }
}
