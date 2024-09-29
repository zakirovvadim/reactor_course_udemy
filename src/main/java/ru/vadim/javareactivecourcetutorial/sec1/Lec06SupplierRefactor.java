package ru.vadim.javareactivecourcetutorial.sec1;

import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import static ru.vadim.javareactivecourcetutorial.courceUtil.Util.faker;
import static ru.vadim.javareactivecourcetutorial.courceUtil.Util.onNext;

public class Lec06SupplierRefactor {

    public static void main(String[] args) {
        getName();
        getName()
                .subscribeOn(Schedulers.boundedElastic())
                .subscribe(onNext());
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
            }return faker().name().fullName();
        }).map(String::toUpperCase);
    }
}
