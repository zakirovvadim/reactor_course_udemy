package ru.vadim.java.reactive.cource.tutorial.sec13;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.util.context.Context;
import ru.vadim.java.reactive.cource.tutorial.courceUtil.Util;

@Slf4j
public class Lec03ContextPropogation {
    public static void main(String[] args) {
        getWelcomeMessage()
//                .concatWith(producer1()) // можно указывать несколько продьюсеров, контекст у них будет сохраняться
//                .concatWith(Flux.merge(producer1(), producer2()))
                .concatWith(Flux.merge(producer1(), producer2().contextWrite(ctx -> Context.empty())))// ели нам нужно чтобы у какого то продьюсеры не было контекста
                // или другой, мы можем отдельно вызывать у него методы связанные с его контекстом
                .contextWrite(Context.of("user", "vadim"))
                .subscribe(Util.subscriber());

        Util.sleepSeconds(2);
    }

    private static Mono<String> getWelcomeMessage() {
        return Mono.deferContextual(ctx -> {
            if (ctx.hasKey("user")) {
                return Mono.just("Welcome %s".formatted(ctx.get("user").toString()));
            }
            return Mono.error(new RuntimeException("unauthenticated"));
        });
    }

    private static Mono<String> producer1() {
        return Mono.<String>deferContextual(ctx -> {
                    log.info("producer1: {}", ctx);
                    return Mono.empty();
                })
                .subscribeOn(Schedulers.boundedElastic());
    }

    private static Mono<String> producer2() {
        return Mono.<String>deferContextual(ctx -> {
                    log.info("producer2: {}", ctx);
                    return Mono.empty();
                })
                .subscribeOn(Schedulers.parallel());
    }
}
