package ru.vadim.javareactivecourcetutorial.sec10;

import reactor.core.publisher.Flux;
import ru.vadim.javareactivecourcetutorial.courceUtil.Util;

import java.time.Duration;
/*
buffer() - позволяет накапливать элементы от продьюсера и выдаватьт их лколлекцией List.
В параметрах можно указать как размер баффера, так и аргумент времени.
Еслои как в примере с демо4 у нас есть Flux(never) в concatWith(т.е. исполнится после take()
значит, что мы никогда не закончим получения данных от паблишера флакс невер, и процес в других демо может зависнуть
полсе получения указанного количества элементов консьмера. Для этого стоит указать bufferTimeout,
после окнчания таймаута мы дополчим остальные элементы.
 */
public class Lec01Buffer {
    public static void main(String[] args) throws InterruptedException {
        demo4();
        Thread.sleep(Duration.ofSeconds(5));
    }

    private static void demo1() {
        eventStream()
                .buffer()
                .subscribe(Util.subscriber());
    }

    private static void demo2() {
        eventStream()
                .buffer(3)
                .subscribe(Util.subscriber());
    }

    private static void demo3() {
        eventStream()
                .buffer(Duration.ofMillis(500))
                .subscribe(Util.subscriber());
    }

    private static void demo4() {
        eventStream()
                .bufferTimeout(3, Duration.ofSeconds(1))
                .subscribe(Util.subscriber());
    }

    private static Flux<String> eventStream() {
        return Flux.interval(Duration.ofMillis(200))
                .take(10)
                .concatWith(Flux.never())
                .map(el -> "element-" + (el + 1));
    }
}
