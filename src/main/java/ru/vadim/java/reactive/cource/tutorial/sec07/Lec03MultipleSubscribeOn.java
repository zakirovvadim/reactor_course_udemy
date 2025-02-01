package ru.vadim.java.reactive.cource.tutorial.sec07;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;
import ru.vadim.java.reactive.cource.tutorial.courceUtil.Util;

@Slf4j

public class Lec03MultipleSubscribeOn {
    public static void main(String[] args) throws InterruptedException {
        Flux<Object> flux = Flux.create(fluxSink -> {
                    for (int i = 0; i < 3; i++) {
                        fluxSink.next(i);
                    }
                    fluxSink.complete();
                })
                .subscribeOn(Schedulers.parallel()) // наиболее близкий к производителю subscribeOn всегда будет иметь приоритет по какому типу следует выполнять
                .doOnNext(i -> log.info("value : {},", i))
                .doFirst(() -> log.info("first1"))
                .subscribeOn(Schedulers.boundedElastic())
                .doFirst(() -> log.info("first2"));

        Runnable runnable = () -> flux
                .subscribe(Util.subscriber());
        Thread.ofPlatform().start(runnable);

        Thread.sleep(5000);
    }
}
