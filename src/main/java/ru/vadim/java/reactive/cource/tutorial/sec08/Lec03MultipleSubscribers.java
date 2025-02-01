package ru.vadim.java.reactive.cource.tutorial.sec08;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;
import ru.vadim.java.reactive.cource.tutorial.courceUtil.Util;

/*
Если на производителя поключены несколько консьюмеров с разной скоростью потребления, тогда реактор сам подстраивается
под работу с каждым из них.
 */
@Slf4j
public class Lec03MultipleSubscribers {
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
                .map(Lec03MultipleSubscribers::timeConsuming)
                .subscribe(Util.subscriber("sub1"));

        producer
                .take(100)
                .publishOn(Schedulers.boundedElastic())
                .subscribe(Util.subscriber("sub2"));

        Util.sleepSeconds(60);
    }

    private static int timeConsuming(int i) {
        Util.sleepSeconds(1);
        return i;
    }
}
