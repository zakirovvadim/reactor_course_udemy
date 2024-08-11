package ru.vadim.updatedCource.sec05;

import reactor.core.publisher.Flux;
import ru.vadim.updatedCource.common.Util;
/*
Похож на defaultIfEmpty, но там используется хардкод того, что мы хотим вернуть при пустом производителе.
Тут же мы можем вызвать метод. В каком случае это полезно, например, в первый раз взять из кеша редис, а в случае отстутсвия
вызвать метод обращенияв постгрес, например.
 */
public class Lec08SwitchIfEmpty {
    public static void main(String[] args) {
        Flux.range(1, 10)
                .filter(i -> i > 10)
                .switchIfEmpty(fallback()) // если ничего не передается, срабатывает дефолтный метод
                .subscribe(Util.subscriber());
    }

    private static Flux<Integer> fallback() {
        return Flux.range(100, 3);
    }
}
