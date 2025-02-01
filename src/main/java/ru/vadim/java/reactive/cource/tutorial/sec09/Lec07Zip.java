package ru.vadim.java.reactive.cource.tutorial.sec09;

import reactor.core.publisher.Flux;
import ru.vadim.java.reactive.cource.tutorial.courceUtil.Util;

import java.time.Duration;
/*
.zip() - позволяет конструировать какой то объект путем полученя по ОДНОМУ элементу из производителей.
Поэтому если один из производителей передает меньшее количество элементов чем другие, то объект уже не создать, и количество
будет равно минимальному количеству элементов какого-либо производителя.
 */
public class Lec07Zip {
    public static void main(String[] args) throws InterruptedException {
        Flux.zip(getBody(), getEngine(), getTires())
                .map(elements -> new Car(elements.getT1(), elements.getT2(), elements.getT3()))
                .subscribe(Util.subscriber());
        Thread.sleep(Duration.ofSeconds(5));
    }

    private record Car(String body, String engine, String tires){}

    private static Flux<String> getBody() {
        return Flux.range(1, 5)
                .map(i -> "-body" + i)
                .delayElements(Duration.ofMillis(100));
    }
    private static Flux<String> getEngine() {
        return Flux.range(1, 1)
                .map(i -> "-engine" + i)
                .delayElements(Duration.ofMillis(200));
    }
    private static Flux<String> getTires() {
        return Flux.range(1, 10)
                .map(i -> "-tires" + i)
                .delayElements(Duration.ofMillis(75));
    }
}
