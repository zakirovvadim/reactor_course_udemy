package ru.vadim.javareactivecourcetutorial.sec08;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;
import reactor.util.concurrent.Queues;
import ru.vadim.javareactivecourcetutorial.courceUtil.Util;
/*
Бэкпрежер в вебфлаксе представлена как наполняемая очередь 256-ю элементами по умолчанию, и если потребитель успеет
потребить >= 75% от этой очереди, тогда производитель вновь запускается и продьюсит элементы в эту очередь.
 */
@Slf4j
public class Lec01BackpressureHandling {
    public static void main(String[] args) {
        System.setProperty("reactor.bufferSize.small", "16"); // проперти с помощью которой можно указать размер очереди, минимум 16

        var producer = Flux.generate(() -> 1,
                (state, sink) -> {
            log.info("producing stat: {}", state);
            sink.next(state);
            return ++state;
                })
                .cast(Integer.class)
                .subscribeOn(Schedulers.parallel());

        producer
                .publishOn(Schedulers.boundedElastic())
                .map(Lec01BackpressureHandling::timeConsuming)
                .subscribe(Util.subscriber());

        Util.sleepSeconds(60);
    }

    private static int timeConsuming(int i) {
        Util.sleepSeconds(1);
        return i;
    }
}
