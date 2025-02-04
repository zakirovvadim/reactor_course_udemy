package ru.vadim.java.reactive.cource.tutorial.sec05;

import reactor.core.publisher.Flux;
import ru.vadim.java.reactive.cource.tutorial.courceUtil.Util;

import java.time.Duration;
import java.util.stream.Stream;

import static ru.vadim.java.reactive.cource.tutorial.courceUtil.Util.subscriber;
/*
publish().refCount(2) - один из способов создать горячую публицкаю. refCount() - это минимальное количество подписчиков
для старта публикации.
 share() это тоже самое что и publish().refCount(1); - т е метод share() это по сути публикация с минимальным одним подписчиком.
Если первый подписчик закончит потребеление от паблишера (и главное не отменит) и если минимум, например, равен одному подписчику,
 далее следующий подписчик снвоа запустит проесс публикации и начнет получать даныне с нуля. Поэтому, когда источник активен,
  если кто-то еще присоединится, все будут получать одни и те же данные.
 */
public class Lec03HotPublisher {
    public static void main(String[] args) throws InterruptedException {
        Flux<String> netflix = Flux.fromStream(Lec03HotPublisher::getStream)
                .delayElements(Duration.ofSeconds(1))
                .publish()
                .refCount(1);

        netflix.subscribe(Util.subscriber("Vadim"));

        Thread.sleep(10000);

        netflix.subscribe(Util.subscriber("Nastya"));

        Thread.sleep(60000);
    }

    private static Stream<String> getStream() {
        return Stream.of("Film 1", "Film 2", "Film 3", "Film 4");
    }
}
