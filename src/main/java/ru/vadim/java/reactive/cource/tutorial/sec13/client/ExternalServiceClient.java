package ru.vadim.java.reactive.cource.tutorial.sec13.client;

import reactor.core.publisher.Mono;
import ru.vadim.java.reactive.cource.tutorial.courceUtil.AbstractHttpClient;

/*
Внешний сервис
 */
public class ExternalServiceClient extends AbstractHttpClient {
    public Mono<String> getBook() {
        return this.httpClient.get()
                .uri("/demo07/book")
                .responseContent()
                .asString()
                .startWith(RateLimiter.limitCalls()) // так как обработка снизу вверх: 2.
                .contextWrite(UserService.userCategoryContext()) // так как обработка снизу вверх: 1. помещяаем в контекст пользователей с их категориями
                .next();
    }
}
