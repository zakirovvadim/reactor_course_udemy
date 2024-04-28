package ru.vadim.javareactivecourcetutorial.section1;

import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;

import static ru.vadim.javareactivecourcetutorial.courceUtil.Util.faker;
import static ru.vadim.javareactivecourcetutorial.courceUtil.Util.onNext;

public class Lec07MonoFromFuture {

    public static void main(String[] args) throws InterruptedException {
        Mono.fromFuture(getName()).subscribe(onNext());
        Thread.sleep(1000);
    }

    private static CompletableFuture<String> getName() {
        return CompletableFuture.supplyAsync(() -> faker().name().fullName());
    }
}
