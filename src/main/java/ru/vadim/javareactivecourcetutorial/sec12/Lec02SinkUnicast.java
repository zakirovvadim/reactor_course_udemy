package ru.vadim.javareactivecourcetutorial.sec12;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;
import ru.vadim.javareactivecourcetutorial.courceUtil.Util;
/*
Sink типа юникаст работает только со дним подписчиком, также к нему можно добавить бекпрешер с кастомной очередью.
Если доабвить второго подписичка то упадет с ошибкой, как в примере демо2ю
 */
public class Lec02SinkUnicast {
    public static void main(String[] args) {
        demo2();
    }

    private static void demo1() {
        Sinks.Many<Object> sink = Sinks.many().unicast().onBackpressureBuffer();
        Flux<Object> flux = sink.asFlux();

        sink.tryEmitNext("hi");
        sink.tryEmitNext("Vadim");
        sink.tryEmitNext("You can");

        flux.subscribe(Util.subscriber("vadim"));
    }
    private static void demo2() {
        Sinks.Many<Object> sink = Sinks.many().unicast().onBackpressureBuffer();
        Flux<Object> flux = sink.asFlux();

        sink.tryEmitNext("hi");
        sink.tryEmitNext("Vadim");
        sink.tryEmitNext("You can");

        flux.subscribe(Util.subscriber("vadim"));
        flux.subscribe(Util.subscriber("настя"));
    }
}
