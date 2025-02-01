package ru.vadim.java.reactive.cource.tutorial.sec09;

import ru.vadim.java.reactive.cource.tutorial.sec09.applications.PaymentService;
import ru.vadim.java.reactive.cource.tutorial.sec09.applications.UserService;
import ru.vadim.updatedCource.common.Util;

/*
flatMap - позволяет объединять зависимых продьюсеров. Если вместо него использовать просто map, тогда получится Mono<Mono<Integer>>
т.е. внутренний продьюсер, данные которого должны быть преобразованы, останутся Моно, а не Стринг - например.
 */
public class Lec09MonoFlatMap {
    public static void main(String[] args) {
        UserService.getuserId("nastya")
                .flatMap(PaymentService::getUserBalance)
                .subscribe(Util.subscriber());
    }
}
