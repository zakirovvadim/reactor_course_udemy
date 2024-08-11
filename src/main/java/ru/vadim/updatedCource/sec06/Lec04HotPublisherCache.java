package ru.vadim.updatedCource.sec06;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import ru.vadim.javareactivecourcetutorial.courceUtil.Util;

import java.time.Duration;

/*
Чистая горячая публикация с autoConnect не позволяет новым подключенным видеть уже опубликованные данные.
Для получения вновь подкюченных данных нужно использовать не publish(), а replay(2). Где параметр - 2 показывать сколько данных до этого
надо закешировать и сразу отдать вновь подключенным.

 */
@Slf4j
public class Lec04HotPublisherCache {
    public static void main(String[] args) {

//        Flux<Integer> stockFlux = stockStream().publish().autoConnect(0);
        Flux<Integer> stockFlux = stockStream().replay(2).autoConnect(0);
        Util.sleepSeconds(4);
        log.info("vadim joining");
        stockFlux.subscribe(Util.subscriber("vadim"));
        Util.sleepSeconds(4);
        log.info("nastya joining");
        stockFlux.subscribe(Util.subscriber("nastya"));
        Util.sleepSeconds(15);
    }

    private static Flux<Integer> stockStream() {
        return Flux.generate(sink -> sink.next(Util.faker().random().nextInt(10, 100)))
                .delayElements(Duration.ofSeconds(3))
                .doOnNext(price -> log.info("emitting price: {}", price))
                .cast(Integer.class);
    }
}
