package ru.vadim.javareactivecourcetutorial.sec12;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;
import ru.vadim.javareactivecourcetutorial.courceUtil.Util;

public class Lec07Replay {
    public static void main(String[] args) {
        demo1();
    }

    private static void demo1() {
        Sinks.Many<Object> sink = getSink2();
        Flux<Object> flux = sink.asFlux();

        flux.subscribe(Util.subscriber("vadim"));
        flux.subscribe(Util.subscriber("настя"));

        sink.tryEmitNext("message 1");
        sink.tryEmitNext("message 2");
        sink.tryEmitNext("message 3");

        Util.sleepSeconds(2);

        flux.subscribe(Util.subscriber("Ilsur"));
        sink.tryEmitNext("message 4");
    }

    private static Sinks.Many<Object> getSink() {
        Sinks.Many<Object> sink = Sinks.many().replay().all(); // replay() - позволяет сохранить и переиграть эмиссию для
        // вновь подключаемых потребителей. Хранится инфомрация в неограниченной очереди.
        return sink;
    }

    private static Sinks.Many<Object> getSink2() {
        Sinks.Many<Object> sink = Sinks.many().replay().limit(2); // можно лимитировать данные указав количество
        // элементов или Duration - время(например последние 2 минуты).
        // в данном случае, если указать лимит элементов 2, потребитель Ильсур получит только мессадж 2, 3 и 4, но не 1
        return sink;
    }
}
