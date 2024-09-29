package ru.vadim.javareactivecourcetutorial.sec02.assignment;

import reactor.core.publisher.Flux;
import ru.vadim.javareactivecourcetutorial.courceUtil.Util;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

public class StockPricePubsher {

    public static Flux<Integer> getPrice() {
        AtomicInteger startPrice = new AtomicInteger(100);
        return Flux.interval(Duration.ofSeconds(1))
                .map(i -> startPrice.getAndAccumulate(
                    Util.faker().random().nextInt(-5, 5),
                    Integer::sum
                ));
    }
}
