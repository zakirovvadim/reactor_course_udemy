package ru.vadim.javareactivecourcetutorial.section05;

import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.stream.Stream;

import static ru.vadim.javareactivecourcetutorial.courceUtil.Util.subscriber;
/*
autoConnect() - публикует сразу, независимо от того кто подключился, если минимальное количество подписчиков указан 0. Если 1 или 2 и т.д
то в зависимости от количества указанных подписчиков.
 */
public class Lec04HotPublishAutoconnect {

    public static void main(String[] args) throws InterruptedException {
        Flux<String> netflix = Flux.fromStream(Lec04HotPublishAutoconnect::getStream)
                .delayElements(Duration.ofSeconds(2))
                .publish()
                .autoConnect(0);

        Thread.sleep(3000);
        netflix.subscribe(subscriber("Vadim"));

        Thread.sleep(7000);

        netflix.subscribe(subscriber("Nastya"));

        Thread.sleep(60000);
    }

    private static Stream<String> getStream() {
        return Stream.of("Film 1", "Film 2", "Film 3", "Film 4", "Film 5", "Film 6");
    }
}
