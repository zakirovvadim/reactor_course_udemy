package ru.vadim.updatedCource.sec05;

import reactor.core.publisher.Flux;
import ru.vadim.updatedCource.common.Util;

public class Lec07DefaultIfEmpty {
    public static void main(String[] args) {
        Flux.range(1, 10)
                .filter(i -> i > 10)
                .defaultIfEmpty(50) // если ничего не передается, срабатывает дефолтный метод
                .subscribe(Util.subscriber());
    }
}
