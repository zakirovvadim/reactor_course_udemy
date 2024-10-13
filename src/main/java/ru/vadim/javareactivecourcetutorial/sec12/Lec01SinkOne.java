package ru.vadim.javareactivecourcetutorial.sec12;

import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;
import ru.vadim.javareactivecourcetutorial.courceUtil.Util;
/* С синк, нам не нужен подписчик, он сам является и издателем и подписчиком. Мы можем просто передавать в него данные,
 превартить в моно
Таким образом, по сути, вы создаете один приемник, через который мы можем передавать данные.
Этот же приемник может действовать как моно, через который мы можем подписываться и получать данные.
 */
public class Lec01SinkOne {
    public static void main(String[] args) {
        demo2();
    }

    private static void demo1() {
        Sinks.One<Object> sink = Sinks.one();
        Mono<Object> mono = sink.asMono();
        mono.subscribe(Util.subscriber());
//        sink.tryEmitValue("hi"); пример эмиссии значения
//        sink.tryEmitEmpty(); пример пустой эмиссии
        sink.tryEmitError(new RuntimeException("oops"));
    }
    private static void demo2() {
        Sinks.One<Object> sink = Sinks.one();
        Mono<Object> mono = sink.asMono();
        sink.tryEmitValue("hi"); // можно вызывать независимо от порядка коде
        mono.subscribe(Util.subscriber("nastya")); // можно использовать несоклько подписчиков, работает как горячая подписка
        mono.subscribe(Util.subscriber("vadim"));
    }

}
