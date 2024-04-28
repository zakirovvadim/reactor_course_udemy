package ru.vadim.javareactivecourcetutorial.section1;

import reactor.core.publisher.Mono;

import static ru.vadim.javareactivecourcetutorial.courceUtil.Util.*;

public class Lec03MonoSubscriber {
    public static void main(String[] args) {
        Mono<Integer> ball = Mono.just("ball")
                        .map(String::length)
                        .map(l -> l / 1);

        ball.subscribe(
                onNext(),
                onError(),
                onComplete()
        );
    }
}
