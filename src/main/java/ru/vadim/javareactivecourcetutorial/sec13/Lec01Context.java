package ru.vadim.javareactivecourcetutorial.sec13;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;
import ru.vadim.javareactivecourcetutorial.courceUtil.Util;

/*
context - позволяет хранить информацию в виде ключ значения, чем то похож на хидеры в http запросах. Подходит для хранения
аунтефикации например.
 .contextWrite(Context.of("user", "b")) - работает снизу в верх
 */
@Slf4j
public class Lec01Context {
    public static void main(String[] args) {
        getWelcomeMessage()
                .contextWrite(Context.of("user", "b"))
                .subscribe(Util.subscriber());
    }

    private static Mono<String> getWelcomeMessage() {
        return Mono.deferContextual(ctx -> {
            if (ctx.hasKey("user")) {
                return Mono.just("Welcome %s".formatted(ctx.get("user").toString()));
            }
            return Mono.error(new RuntimeException("unauthenticated"));
        });
    }
}
