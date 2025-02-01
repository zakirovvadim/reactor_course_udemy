package ru.vadim.java.reactive.cource.tutorial.sec09.helper.mergeUsecase;

import reactor.core.publisher.Flux;

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
