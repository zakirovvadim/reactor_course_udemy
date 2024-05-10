package ru.vadim.javareactivecourcetutorial.section03;

import reactor.core.publisher.Flux;
import ru.vadim.javareactivecourcetutorial.courceUtil.Util;

import static ru.vadim.javareactivecourcetutorial.courceUtil.Util.faker;
import static ru.vadim.javareactivecourcetutorial.courceUtil.Util.onNext;

// Тут используем генератор, и он уже обеспечивает передачу нам каждый раз нового экземпляра synchronousSink. И сам овтечает за цикл.
// Без take или ошибки или complete внутри synchronousSink будет выоплняться бесконечно, т.е. бвсе время выдавать нвоый экземпляр
public class Lec05FluxGenerator {

    public static void main(String[] args) {
//        Flux.generate(synchronousSink -> {
//                    System.out.println("emitting");
//                    synchronousSink.next(Util.faker().country().name());
//                })
//                .take(3)
//                .subscribe(onNext());


        // еще один пример
        Flux.generate(synchronousSink -> {
            String name = faker().country().name();
            synchronousSink.next(name);
        })
                .map(Object::toString)
                .handle((name, sync) -> {
            if (name.equalsIgnoreCase("russian federation")) {
                sync.next(name);
                sync.complete();
            } else {
                sync.next(name);
            }
        })
                .subscribe(onNext());
    }
}