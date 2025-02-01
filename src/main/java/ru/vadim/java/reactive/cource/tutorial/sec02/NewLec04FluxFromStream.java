package ru.vadim.java.reactive.cource.tutorial.sec02;

import reactor.core.publisher.Flux;
import ru.vadim.java.reactive.cource.tutorial.courceUtil.Util;

import java.util.List;
import java.util.stream.Stream;
/*
Используя стрим, надо помнить, что он одноразовый. Поэтому нужно создавать каждый раз новый стрим через supplier
 */
public class NewLec04FluxFromStream {
    public static void main(String[] args) {
        List<Integer> integers = List.of(1, 2, 3, 4, 5);

        Stream<Integer> stream = integers.stream();

//        Flux<Integer> integerFlux = Flux.fromStream(stream);
//        integerFlux.subscribe(Util.subscriber());
//        integerFlux.subscribe(Util.subscriber());

        Flux<Integer> integerFlux1 = Flux.fromStream(() -> integers.stream()); // создаем стрим через сапплаер с каждым вызовом элемента флакс
        integerFlux1.subscribe(Util.subscriber());
        integerFlux1.subscribe(Util.subscriber());
    }
}
