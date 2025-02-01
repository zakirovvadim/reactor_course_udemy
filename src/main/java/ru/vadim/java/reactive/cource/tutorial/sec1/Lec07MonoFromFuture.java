package ru.vadim.java.reactive.cource.tutorial.sec1;

import reactor.core.publisher.Mono;
import ru.vadim.java.reactive.cource.tutorial.courceUtil.Util;

import java.util.concurrent.CompletableFuture;

/*
CompletableFuture по умолчанию не ленивый, поэтому для ленивости нужно в метод fromFuture передать сапплаер
 */
public class Lec07MonoFromFuture {

    public static void main(String[] args) throws InterruptedException {
        Mono.fromFuture(() -> getName());
//                .subscribe(Util.subscriber());
        Util.sleepSeconds(1l);
    }

    private static CompletableFuture<String> getName() {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("generating name");
            return Util.faker().name().fullName();
        });
    }
}
