package ru.vadim.java.reactive.cource.tutorial.sec11;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.util.retry.Retry;
import ru.vadim.java.reactive.cource.tutorial.courceUtil.Util;
import ru.vadim.java.reactive.cource.tutorial.sec11.assingment.ExternalServices;
import ru.vadim.java.reactive.cource.tutorial.sec11.assingment.ServerError;

import java.time.Duration;
// Примеры работы репит и ретрай с внешним сервисом
@Slf4j
public class Lec03ExternalServices {

    public static void main(String[] args) throws InterruptedException {
        Flux.just("a")
                .repeat(1)
                .repeat(2)
                .subscribe(Util.subscriber());
        Thread.sleep(Duration.ofSeconds(60));
    }

    private static void repeat() {
        ExternalServices externalServices = new ExternalServices();
        externalServices.getCountry()
                .repeat()
                .takeUntil(c -> c.equalsIgnoreCase("canada"))
                .subscribe(Util.subscriber());
    }

    private static void retry() {
        ExternalServices externalServices = new ExternalServices();
        externalServices.getProductName(2)
                .retryWhen(retryOnServerError())
                .subscribe(Util.subscriber());
    }

    private static Retry retryOnServerError() {
        return Retry.fixedDelay(20, Duration.ofSeconds(1))
                .filter(ex -> ServerError.class.equals(ex.getClass()))
                .doBeforeRetry(rs -> log.info("retrying {}", rs.failure().getMessage()));
    }
}
