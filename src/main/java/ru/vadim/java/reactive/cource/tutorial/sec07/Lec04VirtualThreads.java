package ru.vadim.java.reactive.cource.tutorial.sec07;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;
import ru.vadim.java.reactive.cource.tutorial.courceUtil.Util;

/*
По умолчнию subscribeOn работает не в виртуальном потоке, для переключения на работу в вирутальных потоках нужно использовать настройку
System.setProperty("reactor.schedulers.defaultBoundedElasticOnVirtualThreads", "true");
Нужно помнить, что виртуальные потоки не подходят для сложной работы ЦП, а больше там где блокируемые операции,
для таких блокируемых операций подходит в том числе и Schedulers.boundedElastic() (Schedulers.elastic()), которые и рекомендуется
использовать с виртуальными потоками.
 */

@Slf4j
public class Lec04VirtualThreads {
    public static void main(String[] args) throws InterruptedException {


        System.setProperty("reactor.schedulers.defaultBoundedElasticOnVirtualThreads", "true");


        Flux<Object> flux = Flux.create(fluxSink -> {
                    for (int i = 0; i < 3; i++) {
                        fluxSink.next(i);
                    }
                    fluxSink.complete();
                })
                .doOnNext(i -> log.info("value : {},", i))
                .doFirst(() -> log.info("first1 - is Virtual thread - {}", Thread.currentThread().isVirtual()))
                .subscribeOn(Schedulers.boundedElastic())
                .doFirst(() -> log.info("first2"));

        Runnable runnable = () -> flux
                .subscribe(Util.subscriber());
        Thread.ofPlatform().start(runnable);

        Thread.sleep(5000);
    }
}
