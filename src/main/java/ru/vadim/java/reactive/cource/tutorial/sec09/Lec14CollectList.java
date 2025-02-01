package ru.vadim.java.reactive.cource.tutorial.sec09;

import reactor.core.publisher.Flux;
import ru.vadim.java.reactive.cource.tutorial.courceUtil.Util;

/*
Собираем элементы от продьюсера, потребляем их в коллекцию и выдаем, когда получем сигнал complete. Неблокирующий.
Нужно учитывать, что если огромное количество, возможно переполнение хипа.
 */
public class Lec14CollectList {
    public static void main(String[] args) {
        Flux.range(1, 10)
                .collectList()
                .subscribe(Util.subscriber());
    }
}
