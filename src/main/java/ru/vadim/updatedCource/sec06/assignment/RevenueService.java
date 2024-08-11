package ru.vadim.updatedCource.sec06.assignment;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Getter
public class RevenueService implements OrderProcessor {

    private final Map<String, Integer> db = new HashMap<>();

    @Override
    public void consume(Order order) {
        Integer currentRevenue = db.getOrDefault(order.category(),0);
        Integer updateRevenue = currentRevenue + order.price();
        db.put(order.category(), updateRevenue);
    }

    @Override
    public Flux<String> stream() {
        return Flux.interval(Duration.ofSeconds(2))
                .map(i -> this.db.toString());
    }
}
