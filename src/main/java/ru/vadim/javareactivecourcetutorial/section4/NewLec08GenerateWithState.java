package ru.vadim.javareactivecourcetutorial.section4;

import reactor.core.publisher.Flux;
import ru.vadim.javareactivecourcetutorial.courceUtil.Util;

/*
Проджект Реактор также опзволяет нам передавать состояния первым параметром: "() -> 0", это также может быть не ноль, а любой объект,
например подключение к базе. Вторым параметром лямбды это сам объект - "counter".
 */
public class NewLec08GenerateWithState {
    public static void main(String[] args) {
        Flux.generate(() -> 0,
                (counter, sink) -> {
                    var country = Util.faker().country().name();
                    sink.next(country);
                    if (counter == 10 || country.equalsIgnoreCase("canada")) {
                        sink.complete();
                    }
                    return counter;
                }).subscribe(Util.subscriber());
    }
}
