package ru.vadim.java.reactive.cource.tutorial.sec12;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;
import ru.vadim.java.reactive.cource.tutorial.courceUtil.Util;

public class Lec04Multicast {
    public static void main(String[] args) {
        demo2();
    }

    private static void demo1() {
        /*
        Мультикаст позволяет проводить эмиссию для несокльких подписчиков, однако, кто подпишется после, не получит сообщения "до".
        бекпрешшерБуффер - в мультикасте работает с ограниченной (bounded) очередь (по умолчанию 256, можно установить свой).
        Если подписчиков нет, как примере демо2, эмиссия копится в эту очередь, и если она переполнится, сообщения теряются.
         */
        Sinks.Many<Object> sink = Sinks.many().multicast().onBackpressureBuffer();
        Flux<Object> flux = sink.asFlux();

        flux.subscribe(Util.subscriber("vadim")); // подписались два человека
        flux.subscribe(Util.subscriber("настя"));

        sink.tryEmitNext("message 1"); // два подписчика выше получат сообщение
        sink.tryEmitNext("message 2");
        sink.tryEmitNext("message 3");

        Util.sleepSeconds(2);

        flux.subscribe(Util.subscriber("Ilsur")); // дальше подписывается Ильсур, но он не оплучит эмиссии произведенные выше
        sink.tryEmitNext("message 4"); // все в том числе и Ильсур получит это сооюбщение.
    }

    private static void demo2() {
        /*
        Если мы сделаем эмиссию без подписчков, сообщения сохранятся в очереди, и сразу все отправятся первому из подписчиков.
        Поэтому подписчик Вадим получит первые три мессаджа сразу.
         */
        Sinks.Many<Object> sink = Sinks.many().multicast().onBackpressureBuffer();
        Flux<Object> flux = sink.asFlux();

        sink.tryEmitNext("message 1");
        sink.tryEmitNext("message 2");
        sink.tryEmitNext("message 3");

        Util.sleepSeconds(2);

        flux.subscribe(Util.subscriber("vadim"));
        flux.subscribe(Util.subscriber("настя"));
        flux.subscribe(Util.subscriber("Ilsur"));

        sink.tryEmitNext("message 4");

        /*
        Пример вывода
        vadim - Received message 1
        vadim - Received message 2
        vadim - Received message 3
        vadim - Received message 4
        настя - Received message 4
        Ilsur - Received message 4
         */
    }
}
