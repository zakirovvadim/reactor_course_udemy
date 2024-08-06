package ru.vadim.javareactivecourcetutorial.sec05;

import reactor.core.publisher.Mono;
import ru.vadim.updatedCource.common.Util;

import java.time.Duration;

public class Lec09Timeout {
    public static void main(String[] args) {

//        getProductName()
//                .timeout(Duration.ofSeconds(1)) // можем установить таймаут на получение от производителя и это выдаст исключение времени ожидания
//                .onErrorReturn("fallback")  // исключение отлавливается и возвращается хардкод
//                .subscribe(Util.subscriber());

        getProductName()
                .timeout(Duration.ofSeconds(1), fallback()) // можем установить таймаут на получение от производителя и выполнение запасного метода
                .subscribe(Util.subscriber());

        ru.vadim.javareactivecourcetutorial.courceUtil.Util.sleepSeconds(5);

    }

    private static Mono<String> getProductName() {
        return Mono.fromSupplier(() -> "service-" +  Util.faker().commerce().productName())
                .delayElement(Duration.ofMillis(1900));
    }

    private static Mono<String> fallback() {
        return Mono.fromSupplier(() -> "fallback-" + Util.faker().commerce().department())
                .delayElement(Duration.ofMillis(300));
    }
}
