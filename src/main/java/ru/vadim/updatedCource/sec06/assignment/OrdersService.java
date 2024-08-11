package ru.vadim.updatedCource.sec06.assignment;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import ru.vadim.javareactivecourcetutorial.section02.client.ExternalServiceClient;

@Slf4j
public class OrdersService {

    private Flux<Order> ordersStream;

    public Flux<Order> getOrders() {
        if (ordersStream == null) {
            ordersStream = orderExecute();
        }
        return ordersStream;
    }

    private Flux<Order> orderExecute() {
        ExternalServiceClient client = new ExternalServiceClient();
        return client.getOrdersStream()
                .doOnError(ex -> log.error(ex.getMessage()))
                .map(this::parse)
                .publish()
                .refCount(2);
    }

    private Order parse(String strOrder) {
        String[] split = strOrder.split(":");
        return new Order(split[1], Integer.parseInt(split[2]), Integer.parseInt(split[3]));
    }
}
