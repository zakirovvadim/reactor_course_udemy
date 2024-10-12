package ru.vadim.javareactivecourcetutorial.sec11;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;
import ru.vadim.updatedCource.common.Util;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class Lec02Retry {
    public static void main(String[] args) throws InterruptedException {

        demo2();

        Thread.sleep(Duration.ofSeconds(10));
    }

    private static void demo1() {
        getCountryName()
                .retry(2) // обычный ретрай с указанием 2 попыток, без аргумента, ретраи будут бесконечны
                .subscribe(Util.subscriber());
    }

    private static void demo2() {
        getCountryName()
                .retryWhen(
                        Retry.fixedDelay(2, Duration.ofSeconds(1)) // можем поставить условия ретрая, например количество ппоыток и задержку по времени между попытками
                                .filter(ex -> RuntimeException.class.equals(ex.getClass())) // можем установить тип исключения, с которым будем пытаться ретраить
                                .onRetryExhaustedThrow((spec, signal) -> signal.failure()) // можем не выводить в консоль ошибку в случае исчерпания попыток, а прокинуть ошибку подписчику
                )
                .subscribe(Util.subscriber());
    }

    private static Mono<String> getCountryName() {
        var atomicInteger = new AtomicInteger(0);
        return Mono.fromSupplier(() -> {
            if (atomicInteger.incrementAndGet() < 5) {
                throw new RuntimeException("oops");
            }
            return Util.faker().country().name();
        })
                .doOnError(err -> log.info("ERROR: {}", err.getMessage()))
                .doOnSubscribe(s -> log.info("subscribing"));
    }
}
