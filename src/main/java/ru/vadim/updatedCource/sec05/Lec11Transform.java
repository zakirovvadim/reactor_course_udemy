package ru.vadim.updatedCource.sec05;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import ru.vadim.updatedCource.common.Util;

import java.util.function.Function;
import java.util.function.UnaryOperator;


@Slf4j
public class Lec11Transform {

    record Customer (int id, String name){}
    record PurchaseOrder(String productName, int price, int quantity){}

    public static void main(String[] args) {
        var isDebuggerEnable = false;
        getCustomers()
                .transform(isDebuggerEnable ? addDebugger() : Function.identity())
                .subscribe();

        getPurchaseOrders()
                .transform(addDebugger())
                .subscribe();
    }

    private static Flux<Customer> getCustomers() {
        return Flux.range(1, 3)
                .map(i -> new Customer(i, Util.faker().name().firstName()));
    }

    private static Flux<PurchaseOrder> getPurchaseOrders() {
        return Flux.range(1, 5)
                .map(i -> new PurchaseOrder(Util.faker.commerce().productName(), i, i * 10));
    }

    private static <T> UnaryOperator<Flux<T>> addDebugger() {
        return flux -> flux
                .doOnNext(i -> log.info("received: {}", i))
                .doOnComplete(() -> log.info("completed"))
                .doOnError(err -> log.error("error", err));
    }
}
