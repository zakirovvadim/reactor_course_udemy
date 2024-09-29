package ru.vadim.javareactivecourcetutorial.sec10.assingment.grouping;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

public class PurchaseOrderService {

    private static final Map<String, UnaryOperator<Flux<PurchaseOrder>>> PROCESSOR_MAP = Map.of(
            "Kids", kidsProcessing(),
            "Automotive", automotiveProcessing());

    public static UnaryOperator<Flux<PurchaseOrder>> automotiveProcessing() {
        return flux -> flux
                .map(po -> new PurchaseOrder(po.item(), po.category(), po.price() + 100));
    }
    public static UnaryOperator<Flux<PurchaseOrder>> kidsProcessing() {
        return flux -> flux
                .flatMap(kids -> getFreeKidsOrder(kids).flux().startWith(kids));
    }

    public static Mono<PurchaseOrder> getFreeKidsOrder(PurchaseOrder po) {
        return Mono.fromSupplier(() -> new PurchaseOrder(po.item() + "-FREE", po.category(), 0));
    }

    public static Predicate<PurchaseOrder> canProcess() {
        return po -> PROCESSOR_MAP.containsKey(po.category());
    }

    public static UnaryOperator<Flux<PurchaseOrder>> getProcessor(String category) {
        return PROCESSOR_MAP.get(category);
    }
}
