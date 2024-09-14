package ru.vadim.javareactivecourcetutorial.sec09;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.vadim.javareactivecourcetutorial.section02.client.ExternalServiceClient;
import ru.vadim.updatedCource.common.Util;

import java.time.Duration;

public class Lec12FlatMapAssingment {
    public static void main(String[] args) throws InterruptedException {
        ExternalServiceClient service = new ExternalServiceClient();
        Flux.range(1, 10)
                .flatMap(service::getProduct, 10)
                .subscribe(Util.subscriber());

        Thread.sleep(Duration.ofSeconds(20));
    }
}
