package ru.vadim.javareactivecourcetutorial.sec11;

import org.reactivestreams.Subscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.vadim.updatedCource.common.Util;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

/*
repeat() - повторяет переподписку на паблишера ПОСЛЕ получения команды complete. Также после этой команлы моно будет флаксом
 */
public class Lec01Repeat {
    public static void main(String[] args) throws InterruptedException {
        demo6();

        Thread.sleep(Duration.ofSeconds(10));
    }

    private static void demo1() {
//        for (int i = 0; i < 100; i++) { создает по сути одновременно несколько подписок
        getCountryName().repeat(3)
                .subscribe( Util.subscriber());
//        }
    }

    private static void demo2() {
        getCountryName()
                .repeat(3) // можно указать количество повторов
                .subscribe(Util.subscriber());
    }

    private static void demo3() {
        getCountryName()
                .repeat()
                .takeUntil(i -> i.equalsIgnoreCase("Russian Federation")) // можно указать условие, до которого должен выполняться репит
                .subscribe(Util.subscriber());
    }

    private static void demo4() {
        AtomicInteger i = new AtomicInteger();
        getCountryName()
                .repeat(() -> i.incrementAndGet() < 3) // можно обуславливать состоянием, которое хранится отдельно
                .subscribe(Util.subscriber());
    }

    private static void demo5() {
        AtomicInteger i = new AtomicInteger();
        getCountryName()
                .repeatWhen(flux -> flux.delayElements(Duration.ofSeconds(1))// можно ограничивать временем, repeatWhen принимает флакс
                        .take(4))// можно на этот флакс добавить огранчиение, сколько хотим взять элементов
                .subscribe(Util.subscriber());
    }

    private static void demo6() { //помимо Моно, репит можно устанавливать и на флакс
        Flux.range(1, 4)
                .repeat(3)
                .subscribe(Util.subscriber());
    }

    private static Mono<String> getCountryName() {
        return Mono.fromSupplier(() -> Util.faker().country().name());
    }
}
