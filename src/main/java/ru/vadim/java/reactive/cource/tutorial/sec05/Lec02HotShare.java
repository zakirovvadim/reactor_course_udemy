package ru.vadim.java.reactive.cource.tutorial.sec05;

import reactor.core.publisher.Flux;
import ru.vadim.java.reactive.cource.tutorial.courceUtil.Util;

import java.time.Duration;
import java.util.stream.Stream;

import static ru.vadim.java.reactive.cource.tutorial.courceUtil.Util.subscriber;
/*
В хот паблишер, данные публикуются как в телевизоре, тот кто подключился позже, получает контент который идет уже сейчас,
но не предыдущие серии
 */
public class Lec02HotShare {

    public static void main(String[] args) throws InterruptedException {

        Flux<String> netflix = Flux.fromStream(Lec02HotShare::getStream)
                .delayElements(Duration.ofSeconds(1))
                .share(); // для горячей публикации

        netflix.subscribe(Util.subscriber("Vadim"));

        Thread.sleep(3000);

        netflix.subscribe(Util.subscriber("Nastya"));

        Thread.sleep(60000);
    }

    private static Stream<String> getStream() {
        return Stream.of("Film 1", "Film 2", "Film 3", "Film 4");
    }
}
