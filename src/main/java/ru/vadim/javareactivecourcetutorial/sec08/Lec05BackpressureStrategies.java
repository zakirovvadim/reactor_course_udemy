package ru.vadim.javareactivecourcetutorial.sec08;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;
import ru.vadim.javareactivecourcetutorial.courceUtil.Util;

import java.time.Duration;
/*
Стратегии противодавления
буфер - .onBackpressureBuffer() у потребителя позволяет накапливать перепроизводимые данные продьюсером.
        Стратегия буфера будет полезна, когда у производителя случаются случайные всплески производства.
        Таким образом, благодаря периодическим всплескам мы можем хранить все эти данные.
        А абонент будет обрабатывать все данные с постоянной скоростью и в конце концов догонит

 */
@Slf4j
public class Lec05BackpressureStrategies {
    public static void main(String[] args) {
        var producer = Flux.create(fluxSink -> {
                    for (int i = 0; i < 500 && !fluxSink.isCancelled(); i++) {
                        log.info("generating: {}", i);
                        fluxSink.next(i);
                        Util.sleep(Duration.ofMillis(50));
                    }
                    fluxSink.complete();
                })
                .cast(Integer.class)
                .subscribeOn(Schedulers.parallel());

        producer
                .onBackpressureBuffer()
                .limitRate(1)
                .publishOn(Schedulers.boundedElastic())
                .map(Lec05BackpressureStrategies::timeConsuming)
                .subscribe();

        Util.sleepSeconds(60);
    }

    private static int timeConsuming(int i) {
        log.info("consuming: {}", i);
        Util.sleepSeconds(1);
        return i;
    }
}
