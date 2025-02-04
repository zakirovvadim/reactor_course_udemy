package ru.vadim.java.reactive.cource.tutorial.sec4;

import reactor.core.publisher.Flux;
import ru.vadim.java.reactive.cource.tutorial.courceUtil.Util;

/**
Ораничение на передачу продюсером данных, следующий реквест на получение идет после получения 75 процентов от заявленных,
 можно изменить вторым аргментом этот параметр, например 99
 */
public class Lec06FluxLmit {
    public static void main(String[] args) {
        Flux.range(1, 1000)
                .log()
                .limitRate(100, 99) // default = 75%, если поставить 100, будет вести себя как при 75%, вместо этого можно писать ноль.
                .subscribe(Util.onNext());
    }
}
