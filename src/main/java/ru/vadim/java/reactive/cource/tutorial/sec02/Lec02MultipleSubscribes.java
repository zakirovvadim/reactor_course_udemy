package ru.vadim.java.reactive.cource.tutorial.sec02;

import reactor.core.publisher.Flux;

public class Lec02MultipleSubscribes {

    public static void main(String[] args) {
        Flux<Integer> flux = Flux.just(1, 2, 3, 4,5,6,7,8,9,10);

        Flux<Integer> filterFlux = flux.filter(i -> i % 2 == 0);
        flux.subscribe(i -> System.out.println("sub 01 : " + i));
        filterFlux.subscribe(i -> System.out.println("sub 02 : " + i));
    }
}
