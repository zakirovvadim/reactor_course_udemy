package ru.vadim.javareactivecourcetutorial.sec09;

import reactor.core.publisher.Flux;
import ru.vadim.javareactivecourcetutorial.section02.client.ExternalServiceClient;
import ru.vadim.updatedCource.common.Util;

import java.time.Duration;
/*
concatMap нужен для сохранения последовательности. Т.е он внутри себя ожидает исчерпание продьюсера,
который к нему подключается, а после этого подходит к объединению следующего продьюсера, поэтому это может занять в данном примере
10 секунд, так как все запросы выполняются поочередно.
 */
public class Lec13ConcatMap {
    public static void main(String[] args) throws InterruptedException {
        ExternalServiceClient service = new ExternalServiceClient();
        Flux.range(1, 10)
                .concatMap(service::getProduct)
                .subscribe(Util.subscriber());

        Thread.sleep(Duration.ofSeconds(20));
    }
}
