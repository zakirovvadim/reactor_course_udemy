package ru.vadim.javareactivecourcetutorial.sec4;

import reactor.core.publisher.Flux;
import ru.vadim.javareactivecourcetutorial.courceUtil.Util;

public class Lec09SwitchIfEmpty {

    public static void main(String[] args) {
        getOrderNumbers()
                .filter(i -> i > 10)
                .switchIfEmpty(fallback()) // тоже самое что и дефолтИфЭмпти только принимает паблишер, у нас это фаллбэк()
                .subscribe(Util.onNext());
    }

    // тут например редис кеш, и сначала ищет тут
    private static Flux<Integer> getOrderNumbers() {
        return Flux.range(1, 10); //
    }

    // если не находит идет в базу тут например бд постгрес
    private static Flux<Integer> fallback() {
        return Flux.range(20, 5); // запасной путь
    }
}
