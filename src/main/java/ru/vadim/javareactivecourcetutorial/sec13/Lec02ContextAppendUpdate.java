package ru.vadim.javareactivecourcetutorial.sec13;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;
import ru.vadim.javareactivecourcetutorial.courceUtil.Util;

import java.util.Map;

@Slf4j
public class Lec02ContextAppendUpdate {

    public static void main(String[] args) {
        getWelcomeMessage()
                .contextWrite(ctx -> Context.empty()) // можно вычищать контекст
                .contextWrite(ctx -> ctx.delete("c")) // можно удалять из контекста по ключу
                .contextWrite(Context.of("user", "vadim")) // можно менять  мапу контекста по ключу
                .contextWrite(Context.of("a", "b").put("c", "d").put("e", "f")) // можно доплнять контекст (контекст работает снизу вверху)
                .contextWrite(Context.of("user", "sam"))
                .subscribe(Util.subscriber());
    }

    private static void append() {
        getWelcomeMessage()
                .contextWrite(Context.of("a", "b").put("c", "d").put("e", "f")) // можно доплнять контекст (контекст работает снизу вверху)
                .contextWrite(Context.of("user", "sam"))
                .subscribe(Util.subscriber());
    }

    private static Mono<String> getWelcomeMessage() {
        return Mono.deferContextual(ctx -> {
            log.info("{}", ctx);
            if (ctx.hasKey("user")) {
                return Mono.just("Welcome %s".formatted(ctx.get("user").toString()));
            }
            return Mono.error(new RuntimeException("unauthenticated"));
        });
    }
}
