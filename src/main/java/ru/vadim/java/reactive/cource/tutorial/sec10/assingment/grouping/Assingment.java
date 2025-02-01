package ru.vadim.java.reactive.cource.tutorial.sec10.assingment.grouping;

import reactor.core.publisher.Flux;
import ru.vadim.updatedCource.common.Util;

import java.time.Duration;

public class Assingment {
    public static void main(String[] args) throws InterruptedException {
        orderStream()
                .filter(PurchaseOrderService.canProcess())
                .groupBy(PurchaseOrder::category)
                .flatMap(gf -> gf.transform(PurchaseOrderService.getProcessor(gf.key())))
                .subscribe(Util.subscriber());

        Thread.sleep(Duration.ofSeconds(39));
    }

    private static Flux<PurchaseOrder> orderStream() {
        return Flux.interval(Duration.ofMillis(200))
                .map(i -> PurchaseOrder.create());
    }

}
