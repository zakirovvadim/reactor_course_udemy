package ru.vadim.javareactivecourcetutorial.sec09.helper.mergeUsecase;

import reactor.core.publisher.Flux;
import ru.vadim.updatedCource.common.Util;

import java.time.Duration;

public class Aviasales {

    public static Flux<Flight> getFlights() {
        return Flux.merge(
                        Aeroflot.getFlight(),
                        UralAirlines.getFlight(),
                        Pobeda.getFlight()
                )
                .take(Duration.ofSeconds(2));
    };
}
