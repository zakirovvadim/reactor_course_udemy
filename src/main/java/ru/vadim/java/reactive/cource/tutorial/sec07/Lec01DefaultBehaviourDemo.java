package ru.vadim.java.reactive.cource.tutorial.sec07;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

/*
Таким образом, по умолчанию тот поток, кто оформляет подписку, будет выполнять всю работу.
 */
@Slf4j
public class Lec01DefaultBehaviourDemo {
    public static void main(String[] args) {
        Flux<Integer> flu = Flux.create(sink -> {
                    for (int i = 0; i < 3; i++) {
                        log.info("generatin: {}", i);
                        sink.next(i);
                    }
                    sink.complete();
                })
                .cast(Integer.class)
                .doOnNext(v -> log.info("value: {}", v));

        /*
        flu.subscribe(Util.subscriber("sub1")); // Поток main выполняет все эти подписки
        flu.subscribe(Util.subscriber("sub2"));
         */
//        Runnable runnable  = () -> flu.subscribe(Util.subscriber("sub1")); Подписывается на получение данных не основнйо поток, а новый, тогда в логах будет выполняться Thread-0.Таким образом, по умолчанию тот, кто оформляет подписку, будет выполнять всю работу.
//        Thread.ofPlatform().start(runnable);
    }
}
