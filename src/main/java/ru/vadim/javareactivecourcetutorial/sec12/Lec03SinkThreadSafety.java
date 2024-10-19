package ru.vadim.javareactivecourcetutorial.sec12;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;
import ru.vadim.javareactivecourcetutorial.courceUtil.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/*
Синк формально потокобезопасен, но в случае попытке закинуть данные в непотокобезопасную коллекцию будут проблемы с
потокообезопасностью.
В документации написано: Если они не созданы с помощью unsafe()спецификации, эти приемники потокобезопасны в том смысле,
что они обнаружат одновременный доступ и быстро откажут при одной из попыток. unsafe()С другой стороны, приемники должны
быть синхронизированы извне (обычно путем вызова из контекста, совместимого с Reactive Streams, например Subscriber,
оператора или , что означает, что можно убрать накладные расходы на обнаружение одновременного доступа из самого приемника).

Т.е. Подразумевается, что если не используется вариант Unsafe, приемник поддерживает сериализованную эмиссию,
но на самом деле сериализованная версия просто быстро выходит из строя в случае одновременной эмиссии.
(помни, что Flux.create() - поддерживает потокобезопасную эмиссию)

Поэтому для решения проблемы нужно вызывать у синка не tryEmitNext, с указанием енама EmitResult.FAIL_NON_SERIALIZED, который
падает при сериализации во время конкурентного обращзения к не потокобезопасной коллекции.
 */

@Slf4j
public class Lec03SinkThreadSafety {
    public static void main(String[] args) {
        demo2();
        Util.sleepSeconds(2);
    }

    private static void demo1() {
        Sinks.Many<Object> sink = Sinks.many().unicast().onBackpressureBuffer();
        Flux<Object> flux = sink.asFlux();
        var list = new ArrayList<>();
        flux.subscribe(list::add);

        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            CompletableFuture.runAsync(() -> {
                sink.tryEmitNext(finalI);
            });
        }
        Util.sleepSeconds(2);
        log.info("Size: '{}'", list.size());
    }

    private static void demo2() {
        Sinks.Many<Object> sink = Sinks.many().unicast().onBackpressureBuffer();
        Flux<Object> flux = sink.asFlux();
        var list = new ArrayList<>();
        flux.subscribe(list::add);

        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            CompletableFuture.runAsync(() -> {
                sink.emitNext(finalI, ((signalType, emitResult) -> {
                    return Sinks.EmitResult.FAIL_NON_SERIALIZED.equals(emitResult); // проверяем сериализацию эмиссии и возвращаем булеан
                }));
            });
        }
        Util.sleepSeconds(2);
        log.info("Size: '{}'", list.size());
    }
}
