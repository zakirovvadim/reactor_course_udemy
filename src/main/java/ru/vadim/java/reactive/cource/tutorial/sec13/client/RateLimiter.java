package ru.vadim.java.reactive.cource.tutorial.sec13.client;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
/*
Сервис который ограничивает количество данных для каждого из пользователей, в зависимости от его категории
 */
public class RateLimiter {

    public static final Map<String, Integer> categoryAttempt = Collections.synchronizedMap(new HashMap<>());

    static {
        referesh();
    }
    // ограничитель
    static <T> Mono<T> limitCalls() {
        return Mono.deferContextual(ctx -> { // создаем контекст
            var allowCall = ctx.<String>getOrEmpty("category") // вытаскиваем из контекста категорию или возвращаем пустую строку
                    .map(RateLimiter::canAllow) // проверяем может ли потребитель получить и вычитаем попытки
                    .orElse(false); // иначе возвращаем фолс
            return allowCall ? Mono.empty() : Mono.error(new RuntimeException("exceeded the given limit")); // еесли попытки есть и тру, возвращаем пустую мону с контекстом
        });
    }
    private static boolean canAllow(String category) { // смотрим сколько п+9опыток у пользователя, если есть, отнимаем и возвращаем тру, значит пользователь еще может потребить данные производителя
        Integer attempt = categoryAttempt.getOrDefault(category, 0);
        if (attempt > 0) {
            categoryAttempt.put(category, attempt - 1);
            return true;
        }
        return false;
    }

    // обновляем категории каждые пят секунд
    private static void referesh() {
        Flux.interval(Duration.ofSeconds(5))
                .startWith(0L)
                .subscribe(i -> {
                    categoryAttempt.put("standart", 2);
                    categoryAttempt.put("plus", 3);
                });
    }
}
