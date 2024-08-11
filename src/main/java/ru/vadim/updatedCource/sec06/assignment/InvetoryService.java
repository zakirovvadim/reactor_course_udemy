package ru.vadim.updatedCource.sec06.assignment;

import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class InvetoryService implements OrderProcessor {

    private final Map<String, Integer> db = new HashMap<>();

    @Override
    public void consume(Order order) {
        Integer currentInvetory = db.getOrDefault(order.category(),500);
        Integer updateInvetory = currentInvetory - order.quantity();
        db.put(order.category(), updateInvetory);
    }

    @Override
    public Flux<String> stream() {
        return Flux.interval(Duration.ofSeconds(2))
                .map(i -> this.db.toString());
    }
}
