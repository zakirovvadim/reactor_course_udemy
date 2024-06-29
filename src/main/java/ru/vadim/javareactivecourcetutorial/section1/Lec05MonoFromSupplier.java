package ru.vadim.javareactivecourcetutorial.section1;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import ru.vadim.javareactivecourcetutorial.courceUtil.Util;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.Supplier;

import static ru.vadim.javareactivecourcetutorial.courceUtil.Util.faker;
import static ru.vadim.javareactivecourcetutorial.courceUtil.Util.onNext;

/*
fromCallable - содержит проброс исключения и если в нашем методе будет эксепшн, то используя коллЭйбл не нужно будет писать трай кетч
 */
@Slf4j
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

        List<Integer> integers = List.of(1, 2, 3);
        Mono.fromSupplier(() -> sum(integers))
                .subscribe(Util.subscriber());
    }

    private static String getName() {
        System.out.println("Generating name..");
        String s = faker().name().fullName();
        return s;
    }

    private static int sum(List<Integer> list) {
        log.info("finding the sum of {}", list);
        return list.stream().mapToInt(a -> a).sum();
    }
}
