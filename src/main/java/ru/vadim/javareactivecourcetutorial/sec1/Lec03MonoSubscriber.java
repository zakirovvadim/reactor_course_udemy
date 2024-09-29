package ru.vadim.javareactivecourcetutorial.sec1;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
public class Lec03MonoSubscriber {
    public static void main(String[] args) {
        Mono<Integer> ball = Mono.just(1)
                        .map(l -> l / 1);

//        ball.subscribe(
//                onNext(),
//                onError(),
//                onComplete()
//        );

        ball.subscribe(
                i -> log.info("received: {}", i),
                err -> log.error("error", err),
                () -> log.info("completed"),
                subscription -> subscription.request(1)
        );
    }
}
