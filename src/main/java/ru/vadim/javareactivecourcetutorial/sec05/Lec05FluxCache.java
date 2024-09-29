package ru.vadim.javareactivecourcetutorial.sec05;

import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.stream.Stream;

import static ru.vadim.javareactivecourcetutorial.courceUtil.Util.subscriber;
/*
cache() - позволяет закеширвоать элементы после публикации первому подписчику, а последующему выдать все и сразу
cache() = publish().reply() int.max
 */
public class Lec05FluxCache {
    public static void main(String[] args) throws InterruptedException {
        Flux<String> netflix = Flux.fromStream(Lec05FluxCache::getStream)
                .delayElements(Duration.ofSeconds(1))
                .cache(2);

        Thread.sleep(2000);

        netflix.subscribe(subscriber("Vadim"));

        Thread.sleep(10000);

        netflix.subscribe(subscriber("Nastya"));

        Thread.sleep(60000);
    }

    private static Stream<String> getStream() {
        return Stream.of("Film 1", "Film 2", "Film 3", "Film 4", "Film 5", "Film 6", "Film 7");
    }
}
