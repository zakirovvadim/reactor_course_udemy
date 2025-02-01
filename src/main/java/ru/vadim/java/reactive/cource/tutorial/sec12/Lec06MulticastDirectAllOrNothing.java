package ru.vadim.java.reactive.cource.tutorial.sec12;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;
import ru.vadim.java.reactive.cource.tutorial.courceUtil.Util;

import java.time.Duration;
/*
directAllOrNothing(); - будут отправлено столько элементов, сколько может потребить самы медленный, т.е. либо все всё потребляют
либо никто. В даннмо примере потребитель сем и майк получат по 1 элементу, но далее, так как майк имеет задержку, остальная эмиссия
выпадет с ошибкой.
 */
@Slf4j
public class Lec06MulticastDirectAllOrNothing {
    public static void main(String[] args) {
        demo2();

        Util.sleepSeconds(4);
    }

    private static void demo2() {
        System.setProperty("reactor.bufferSize.small", "16");

        Sinks.Many<Object> sink = Sinks.many().multicast().directAllOrNothing();
        Flux<Object> flux = sink.asFlux();

        flux.subscribe(Util.subscriber("sam"));
        flux
                .delayElements(Duration.ofMillis(200))
                .subscribe(Util.subscriber("mike"));

        for (int i = 1; i <= 100; i++) {
            var result = sink.tryEmitNext(i);
            log.info("item: {}, result: {}", i, result);
        }
    }
}
