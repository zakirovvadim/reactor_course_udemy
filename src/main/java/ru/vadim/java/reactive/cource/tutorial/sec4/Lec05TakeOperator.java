package ru.vadim.java.reactive.cource.tutorial.sec4;

import reactor.core.publisher.Flux;
import ru.vadim.java.reactive.cource.tutorial.courceUtil.Util;

public class Lec05TakeOperator {
    public static void main(String[] args) {
        Flux.range(1, 10)
                .log("take")
                .take(3)
                .log("sub")
                .subscribe(Util.subscriber());

        Flux.range(1, 10)
                .log("take")
                .takeWhile(i -> i < 5) // пока выполняется условие, забирает элементы
                .log("sub")
                .subscribe(Util.subscriber());

        Flux.range(1, 10)
                .log("take")
                .takeUntil(i -> i < 5) // останавливает кгда условие не выполняется, + разрешает пройти последнему элементу.
                .log("sub")
                .subscribe(Util.subscriber());
    }
}
