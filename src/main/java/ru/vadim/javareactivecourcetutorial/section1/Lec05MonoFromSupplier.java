package ru.vadim.javareactivecourcetutorial.section1;

import io.micrometer.observation.Observation;
import reactor.core.publisher.Mono;

import java.util.concurrent.Callable;
import java.util.function.Supplier;

import static ru.vadim.javareactivecourcetutorial.courceUtil.Util.faker;
import static ru.vadim.javareactivecourcetutorial.courceUtil.Util.onNext;

public class Lec05MonoFromSupplier {
    public static void main(String[] args) {
        // use hust only when you have date already
//        Mono<String> mono = Mono.just(getName());

        Supplier<String> stringSupplier = () -> getName();
        Mono<String> stringMono = Mono.fromSupplier(() -> getName());
        stringMono.subscribe(
                onNext()
        );

        Callable<String> stringCallable = () -> getName();
        Mono.fromCallable(stringCallable)
                .subscribe(onNext());
    }

    private static String getName() {
        System.out.println("Generating name..");
        String s = faker().name().fullName();
        return s;
    }
}
