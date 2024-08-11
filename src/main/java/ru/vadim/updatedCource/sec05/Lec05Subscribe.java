package ru.vadim.updatedCource.sec05;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

/*
DВместо помещения методов некст, комплит и иррор внутрь утилитного метода, аналогом может являться вызов ихх напрямую.
 */
@Slf4j
public class Lec05Subscribe {

    public static void main(String[] args) {
        Flux.range(1, 10)
                .doOnNext(i -> log.info("received: {}", i))
                .doOnComplete(() -> log.info("complete"))
                .doOnError(err -> log.error("error", err))
                .subscribe();
    }
}
