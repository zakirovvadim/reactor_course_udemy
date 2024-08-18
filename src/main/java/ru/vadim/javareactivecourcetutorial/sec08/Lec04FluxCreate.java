package ru.vadim.javareactivecourcetutorial.sec08;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;
import ru.vadim.javareactivecourcetutorial.courceUtil.Util;

import java.time.Duration;
/*
Если мы вместо Flux.generate пишем Flux.create, такимобразом мы сами устанавливаем производителя данных. Flux.generate
умеет сам регулировать производство и бекпрежер, Flux.create нет. Потому что после заполнения очереди, потребитель
кладет в другую внутреннюю очередь, которая не видна потребителю. Поэтому может упасть с нехваткой памяти.
Таким образом когда потребитель слишком медленный, а производитель слишком быстрый из-за множественных планировщиков и т. д.,
то, особенно при создании, нет автоматической обработки обратного давления.
И мы можем столкнуться с такого рода странными проблемами.

limitRate с Flux.create не будет работать.
 */
@Slf4j
public class Lec04FluxCreate {
    public static void main(String[] args) {
        System.setProperty("reactor.bufferSize.small", "16"); // проперти с помощью которой можно указать размер очереди, минимум 16

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
                .publishOn(Schedulers.boundedElastic())
                .map(Lec04FluxCreate::timeConsuming)
                .subscribe();

        Util.sleepSeconds(60);
    }

    private static int timeConsuming(int i) {
        log.info("consuming: {}", i);
        Util.sleepSeconds(1);
        return i;
    }
}
