package ru.vadim.java.reactive.cource.tutorial.sec12;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;
import ru.vadim.java.reactive.cource.tutorial.courceUtil.Util;

import java.time.Duration;

@Slf4j
public class Lec05MulticastDirectBestEffort {
    public static void main(String[] args) {
        demo2();
        Util.sleepSeconds(20);
    }

    private static void demo1() {
        /*
        Если потребление осуществляет более медленный потребитель, то в случае не хватки буффера (так как он переполняется изза того,
        что медленный потребитель не успевает) будут падать ошибки.
        Возможным решением может быть увеличение буффера, но медленный потребитель не получит данные то, что получил быстрый потребитель.
         */
        System.setProperty("reactor.bufferSize.small", "16");

        Sinks.Many<Object> sink = Sinks.many().multicast().onBackpressureBuffer(); // можно увеличить буффер
        Flux<Object> flux = sink.asFlux();

        flux.subscribe(Util.subscriber("sam"));
        flux.delayElements(Duration.ofMillis(100)).subscribe(Util.subscriber("mike"));

        for (int i = 1; i <= 100; i++) {
            var result = sink.tryEmitNext(i);
            log.info("item: {}, result: {}", i, result);
        }
    }

    private static void demo2() {
        System.setProperty("reactor.bufferSize.small", "16");

        Sinks.Many<Object> sink = Sinks.many().multicast().directBestEffort();
        Flux<Object> flux = sink.asFlux();

        flux.subscribe(Util.subscriber("sam"));
        flux
                .onBackpressureBuffer() // добавляем отдельный буффер для медленного потребителя, чтобы он получил все данные
                .delayElements(Duration.ofMillis(200))
                .subscribe(Util.subscriber("mike"));

        for (int i = 1; i <= 100; i++) {
            var result = sink.tryEmitNext(i);
            log.info("item: {}, result: {}", i, result);
        }
    }
}
