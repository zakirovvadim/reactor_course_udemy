package ru.vadim.javareactivecourcetutorial.sec02;

import reactor.core.publisher.Flux;

import static ru.vadim.javareactivecourcetutorial.courceUtil.Util.*;

public class Lec01FluxIntro {

    public static void main(String[] args) {
        Flux<Integer> flux = Flux.just(1,2,3,4);
        Flux<Object> flux2 = Flux.just(1,2,3,4, "abc");
        flux.subscribe(onNext(), onError(), onComplete());
    }
}
