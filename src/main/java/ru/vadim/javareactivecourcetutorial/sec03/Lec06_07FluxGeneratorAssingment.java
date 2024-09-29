package ru.vadim.javareactivecourcetutorial.sec03;

import reactor.core.publisher.Flux;

import static ru.vadim.javareactivecourcetutorial.courceUtil.Util.faker;
import static ru.vadim.javareactivecourcetutorial.courceUtil.Util.onNext;

// прим как в генераторе сделать внешний счетчик, ссылка которого будет не доступна во вне, как с атомикИнтеджер
public class Lec06_07FluxGeneratorAssingment {

    public static void main(String[] args) {

        /*
        AtomicInteger atomicInteger = new AtomicInteger(0); // ссылка может использоваться вне лямбды флакса
        Flux.generate(synchronousSink -> {
            String country = faker().country().name();
            synchronousSink.next(country);
            atomicInteger.incrementAndGet();
            if (country.equalsIgnoreCase("russian federation")) synchronousSink.complete();
        }).subscribe(onNext());
         */

        /*
        Flux.generate(
                () -> 1,
                (counter, sink) -> {
                    String country = faker().country().name();
                    sink.next(country);
                    if (counter >= 10 || country.equalsIgnoreCase("russian federation"))
                        sink.complete();

                    return counter + 1;
                }
        ).subscribe(onNext());

         */

        // and use take

        Flux.generate(
                () -> 1,
                (counter, sink) -> {
                    String country = faker().country().name();
                    sink.next(country);
                    if (counter >= 10 || country.equalsIgnoreCase("russian federation"))
                        sink.complete();

                    return counter + 1;
                }
        ).take(3)
                .subscribe(onNext());
    }
}
