package ru.vadim.java.reactive.cource.tutorial;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import reactor.test.publisher.TestPublisher;
import ru.vadim.java.reactive.cource.tutorial.courceUtil.Util;

import java.util.function.UnaryOperator;

public class Lec09PublisherTest {

    private UnaryOperator<Flux<String>> processor() {
        return flux -> flux
                .filter(s -> s.length() > 1)
                .map(String::toUpperCase)
                .map(s -> s + ":" + s.length());
    }

    /*
    тут аналог синка, в паблишер кладем сообщения, во флакс их получаем через подписку
     */
    @Test
    public void publisherTest1() {
        var publisher = TestPublisher.<String>create();
        Flux<String> flux = publisher.flux();

        flux.subscribe(Util.subscriber());

//        publisher.next("a", "b");
//        publisher.complete();

//        publisher.emit("a", "b"); // эмит тот же самый что и некст, но уже содержит комплит

//        publisher.emit("hi", "nastya"); так не будет эмитироваться, так как нет подписчика, нужно поместить в then
        StepVerifier.create(flux.transform(processor()))
                .then(() -> publisher.emit("hi", "nastya"))
                .expectNext("HI:2")
                .expectNext("NASTYA:6")
                .expectComplete()
                .verify();
    }

    @Test
    public void publisherTest2() {
        var publisher = TestPublisher.<String>create();
        Flux<String> flux = publisher.flux();
        flux.subscribe(Util.subscriber());
        StepVerifier.create(flux.transform(processor()))
                .then(() -> publisher.emit("a", "b", "c"))
                .expectComplete()
                .verify();
    }
}
