package ru.vadim.javareactivecourcetutorial.section1;

import reactor.core.publisher.Mono;
import ru.vadim.javareactivecourcetutorial.courceUtil.Util;

import java.util.concurrent.CompletableFuture;

import static ru.vadim.javareactivecourcetutorial.courceUtil.Util.faker;
import static ru.vadim.javareactivecourcetutorial.courceUtil.Util.onNext;

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
            return faker().name().fullName();
        });
    }
}
