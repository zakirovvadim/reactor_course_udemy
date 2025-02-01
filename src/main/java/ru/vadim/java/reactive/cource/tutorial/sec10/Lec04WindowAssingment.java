package ru.vadim.java.reactive.cource.tutorial.sec10;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.vadim.java.reactive.cource.tutorial.sec10.assingment.window.FileWriter;

import java.nio.file.Path;
import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

public class Lec04WindowAssingment {
    public static void main(String[] args) throws InterruptedException {
        var counter = new AtomicInteger(0);
        var fileNameFormat = "src/main/resources/assingment.sec10.window/file%d.txt";

        eventStream().
                window(Duration.ofMillis(1800))
                .flatMap(flux -> FileWriter.create(flux, Path.of(fileNameFormat.formatted(counter.incrementAndGet()))))
                .subscribe();

        Thread.sleep(Duration.ofSeconds(60));
    }

    private static Flux<String> eventStream() {
        return Flux.interval(Duration.ofMillis(500))
                .map(el -> "element-" + (el + 1));
    }

    private static Mono<Void> processEvents(Flux<String> flux) {
        return flux
                .doOnNext(el -> System.out.print("*"))
                .doOnComplete(System.out::println)
                .then();
    }
}
