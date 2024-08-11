package ru.vadim.updatedCource.sec05;

import reactor.core.publisher.Flux;
import ru.vadim.javareactivecourcetutorial.courceUtil.Util;
/*
Иногда нам нужно использовать какую то логику, модицируют данные и могут фильтровать, т.е. и мапа и фильтер в одном.
Для этого можно использовать хендел. Таких модификации как oopsRange можно создавать много - они легковесны.
 */
public class Lec01Handle {
    public static void main(String[] args) {
        Flux<Integer> range = Flux.range(2, 10);
        Flux<Object> oopsRange = range.handle((item, sink) -> { // можем модифицировать флакс и выделить в отдельную переменную,и вызывать отдельно
            if (Integer.valueOf(item) % 2 == 0) {
                sink.next(item);
            } else {
                sink.error(new RuntimeException("oops"));
            }
        });

//        oopsRange.subscribe(Util.subscriber());

        Flux.range(0, 10)
                .handle((item, sink) -> {
                    switch (item) {
                        case 1 -> sink.next(-2);
                        case 4 -> {
                        }
                        case 7 -> sink.error(new RuntimeException("oops"));
                        default -> sink.next(item);
                    }
                })
                .cast(Integer.class)
                .subscribe(Util.subscriber());
    }
}
