package ru.vadim.javareactivecourcetutorial.sec08;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;
import ru.vadim.javareactivecourcetutorial.courceUtil.Util;
/*
Потребитель также может указать ограничитель для производителя с помощью оператора limitRate();
 */
@Slf4j
public class Lec02LimitRate {
    public static void main(String[] args) {
        var producer = Flux.generate(() -> 1,
                        (state, sink) -> {
                            log.info("producing stat: {}", state);
                            sink.next(state);
                            return ++state;
                        })
                .cast(Integer.class)
                .subscribeOn(Schedulers.parallel());

        producer
                .limitRate(1)
                .publishOn(Schedulers.boundedElastic())
                .map(Lec02LimitRate::timeConsuming)
                .subscribe(Util.subscriber());

        Util.sleepSeconds(60);
    }

    private static int timeConsuming(int i) {
        Util.sleepSeconds(1);
        return i;
    }
}
