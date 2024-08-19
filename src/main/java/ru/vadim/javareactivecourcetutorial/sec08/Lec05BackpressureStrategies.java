package ru.vadim.javareactivecourcetutorial.sec08;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.scheduler.Schedulers;
import ru.vadim.javareactivecourcetutorial.courceUtil.Util;

import java.time.Duration;
/*
Стратегии противодавления
буфер - .onBackpressureBuffer() у потребителя позволяет накапливать перепроизводимые данные продьюсером.
        Стратегия буфера будет полезна, когда у производителя случаются случайные всплески производства.
        Таким образом, благодаря периодическим всплескам мы можем хранить все эти данные.
        А абонент будет обрабатывать все данные с постоянной скоростью и в конце концов догонит
ошибка - .onBackpressureError() - выдает исключение, если видит разницу между производством данных и потреблением потребителя.
размерный буфер - .onBackpressureBuffer(10) - после того как он переполнится, выдаст ошибку
drop -   .onBackpressureDrop() - елси, например, установлен лимитРейт, а производитель закидывает больше чем указанный лимит
          элементы просто скипаются, и когда консьюмер подаст реквест на получение второго элемента, ему уже может попасться, например 10, а не второй.
latest - при переполнении производителем, больше чем потребляет консьюмер, он дропает, но оставляет последний свежий элемент

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
                }
//                FluxSink.OverflowStrategy.BUFFER стратегию бекпрежер можно указывать здесь, но тогда она будет для все подписчиков, и они не смогут применять свои
                        )
                .cast(Integer.class)
                .subscribeOn(Schedulers.parallel());

        producer
                .onBackpressureBuffer()
//                .onBackpressureBuffer(10)
//                .onBackpressureError()
//                .onBackpressureDrop()
//                .onBackpressureLatest()
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
