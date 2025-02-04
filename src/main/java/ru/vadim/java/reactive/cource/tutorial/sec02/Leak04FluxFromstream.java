package ru.vadim.java.reactive.cource.tutorial.sec02;

import reactor.core.publisher.Flux;
import ru.vadim.java.reactive.cource.tutorial.courceUtil.Util;

import java.util.List;
import java.util.stream.Stream;

import static ru.vadim.java.reactive.cource.tutorial.courceUtil.Util.onComplete;
import static ru.vadim.java.reactive.cource.tutorial.courceUtil.Util.onError;
//не забывай что стрим одноразовый, и флакс надо делать не из самого стрима, а той коллекции на основе которого
// создается стрим
public class Leak04FluxFromstream {

    public static void main(String[] args) {
        List<Integer> integers = List.of(1, 2, 3, 4, 5);
        Stream<Integer> stream = integers.stream();

//        Flux<Integer> integerFlux = Flux.fromStream(() -> stream);  используя саму ссылку стрима, на втором прогоне упадет с ошибкой
        Flux<Integer> integerFlux = Flux.fromStream(() -> integers.stream());

        integerFlux
                .log()
                .subscribe(
                        Util.onNext(),
                        onError(),
                        onComplete()
                );

        integerFlux
                .subscribe(
                        Util.onNext(),
                        onError(),
                        onComplete()
                );
    }
}
