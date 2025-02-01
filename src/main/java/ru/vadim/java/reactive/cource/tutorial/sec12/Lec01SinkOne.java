package ru.vadim.java.reactive.cource.tutorial.sec12;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;
import ru.vadim.java.reactive.cource.tutorial.courceUtil.Util;

/* С синк, нам не нужен подписчик, он сам является и издателем и подписчиком. Мы можем просто передавать в него данные,
 превартить в моно
Таким образом, по сути, вы создаете один приемник, через который мы можем передавать данные.
Этот же приемник может действовать как моно, через который мы можем подписываться и получать данные.
 */
@Slf4j
public class Lec01SinkOne {
    public static void main(String[] args) {
        demo3();
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

    private static void demo3() {
        Sinks.One<Object> sink = Sinks.one();
        Mono<Object> mono = sink.asMono();

        mono.subscribe(Util.subscriber("nastya"));

        sink.emitValue("hi", ((signalType, emitResult) -> {
            log.info("hi; {}; {}", signalType.name(), emitResult.name());
            return false;
        }));
        sink.emitValue("hello", ((signalType, emitResult) -> {
            log.info("hello; {}; {}", signalType.name(), emitResult.name());
            return true;
        }));
    }

}
