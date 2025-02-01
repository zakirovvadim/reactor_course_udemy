package ru.vadim.java.reactive.cource.tutorial.sec1;

import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import ru.vadim.java.reactive.cource.tutorial.courceUtil.Util;

public class Lec06SupplierRefactor {

    public static void main(String[] args) {
        getName();
        getName()
                .subscribeOn(Schedulers.boundedElastic())
                .subscribe(Util.onNext());
        getName();
    }

    private static Mono<String> getName() {
        System.out.println("Зашелв гетНейм метод");
        return Mono.fromSupplier(() -> {
            System.out.println("Генерирую имя");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }return Util.faker().name().fullName();
        }).map(String::toUpperCase);
    }
}
