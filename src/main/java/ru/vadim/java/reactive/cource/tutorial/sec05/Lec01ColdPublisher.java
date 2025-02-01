package ru.vadim.java.reactive.cource.tutorial.sec05;

import reactor.core.publisher.Flux;
import ru.vadim.java.reactive.cource.tutorial.courceUtil.Util;

import java.time.Duration;
import java.util.stream.Stream;
/*
Холдоный стрим это как нетфликс, каждый независимый потребитель имеет свой отдельный поток данных.
Стоит заметить, что в метод Flux.fromStream() следует передавать вызов метода, а не результат. Потому что если результат
то он используется два раза, а стрим, как известно, одноразовый.
 */

public class Lec01ColdPublisher {

    public static void main(String[] args) throws InterruptedException {

        Flux<String> netflix = Flux.fromStream(Lec01ColdPublisher::getStream).delayElements(Duration.ofSeconds(2));

        netflix.subscribe(Util.onNext());

        Thread.sleep(5);

        netflix.subscribe(Util.onNext());

        Thread.sleep(60000);
    }

    private static Stream<String> getStream() {
        return Stream.of("Film 1", "Film 2", "Film 3", "Film 4");
    }
}
