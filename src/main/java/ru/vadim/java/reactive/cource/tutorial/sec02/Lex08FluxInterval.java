package ru.vadim.java.reactive.cource.tutorial.sec02;

import reactor.core.publisher.Flux;
import ru.vadim.java.reactive.cource.tutorial.courceUtil.Util;

import java.time.Duration;
// Периодическая публикация, интервалом в 1 секунду
public class Lex08FluxInterval {

    public static void main(String[] args) throws InterruptedException {
        Flux.interval(Duration.ofSeconds(1))
                .subscribe(Util.onNext());

        Thread.sleep(3000);
    }
}
