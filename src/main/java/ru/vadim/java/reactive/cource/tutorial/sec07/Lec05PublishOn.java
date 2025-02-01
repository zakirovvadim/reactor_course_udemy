package ru.vadim.java.reactive.cource.tutorial.sec07;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;
import ru.vadim.updatedCource.common.Util;
/*
publishOn используется для нисходящего потока
 */
@Slf4j
public class Lec05PublishOn {
    public static void main(String[] args) throws InterruptedException {

        /*
        19:30:50.979 [Thread-0] INFO ru.vadim.java.reactive.cource.tutorial.sec07.Lec05PublishOn -- first2
        19:30:50.981 [Thread-0] INFO ru.vadim.java.reactive.cource.tutorial.sec07.Lec05PublishOn -- first1
        19:30:50.986 [parallel-1] INFO ru.vadim.java.reactive.cource.tutorial.sec07.Lec05PublishOn -- value : 0,
        19:30:50.987 [parallel-1] INFO ru.vadim.java.reactive.cource.tutorial.sec07.Lec05PublishOn -- value : 1,
        19:30:50.987 [parallel-1] INFO ru.vadim.java.reactive.cource.tutorial.sec07.Lec05PublishOn -- value : 2,
        19:30:50.987 [boundedElastic-1] INFO ru.vadim.updatedCource.common.DefaultSubscriberImpl -- sub1 received : 0
        19:30:50.987 [boundedElastic-1] INFO ru.vadim.updatedCource.common.DefaultSubscriberImpl -- sub1 received : 1
        19:30:50.987 [boundedElastic-1] INFO ru.vadim.updatedCource.common.DefaultSubscriberImpl -- sub1 received : 2
        19:30:50.988 [boundedElastic-1] INFO ru.vadim.updatedCource.common.DefaultSubscriberImpl -- sub1 receieved complete!
         */

        Flux<Object> flux = Flux.create(fluxSink -> {
                    for (int i = 0; i < 3; i++) {
                        fluxSink.next(i);
                    }
                    fluxSink.complete();
                }) // 6. Происходит производство данных
                .publishOn(Schedulers.parallel()) // 5 Игнорируется.  7. Идя вниз увидит что нужно взять потоки из пула параллельного типа
                .doOnNext(i -> log.info("value : {},", i)) // 4. Игнорируется 8. Выполняется
                .doFirst(() -> log.info("first1")) // 3. Делается вторым при восходящем потоке 9. Игнорируется
                .publishOn(Schedulers.boundedElastic()) // 2. при восходящем игнорируется 10. Видит что нужно поменять типа пула потоков
                .doFirst(() -> log.info("first2")); // 1. выполняется первым в восходящем потоке Thread-0 11. Игнорируется

        Runnable runnable = () -> flux
                .subscribe(Util.subscriber("sub1"));
        Thread.ofPlatform().start(runnable);

        Thread.sleep(5000);
    }
}
