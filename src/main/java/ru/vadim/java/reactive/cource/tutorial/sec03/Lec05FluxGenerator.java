package ru.vadim.java.reactive.cource.tutorial.sec03;

import reactor.core.publisher.Flux;
import ru.vadim.java.reactive.cource.tutorial.courceUtil.Util;

/* Тут используем генератор, и он уже обеспечивает передачу нам каждый раз нового экземпляра synchronousSink. И сам овтечает за цикл.
 Без take или ошибки или complete внутри synchronousSink будет выоплняться бесконечно, т.е. бвсе время выдавать нвоый экземпляр
synchronousSink - по сути это высокоуровнеая реализация for (int i= 0; i < число; i++), если мы пропишем take(3), то форич будет
крутится 3 раза. СинхронайзСинк отправляет по одному элементу, и если в нем же мы завершим сообщение complete(), тогда поток сообщение закончится
раньше, чем например если укажем take(4).
*/
public class Lec05FluxGenerator {

    public static void main(String[] args) {
        createToRange2();
//        Flux.generate(synchronousSink -> {
//                    System.out.println("emitting");
//                    synchronousSink.next(Util.faker().country().name());
//                })
//                .take(3)
//                .subscribe(onNext());


        // еще один пример
//        Flux.generate(synchronousSink -> {
//            String name = faker().country().name();
//            synchronousSink.next(name);
//        })
//                .map(Object::toString)
//                .handle((name, sync) -> {
//            if (name.equalsIgnoreCase("russian federation")) {
//                sync.next(name);
//                sync.complete();
//            } else {
//                sync.next(name);
//            }
//        })
//                .subscribe(onNext());
    }

    // new cource - assingment
    private static void createToRange() {
        Flux.generate(synchronousSink -> {
            String country = Util.faker().country().name();
            synchronousSink.next(country);
            if (country.equalsIgnoreCase("canada")) {
                synchronousSink.complete();
            }
        }).subscribe(Util.subscriber());
    }

    private static void createToRange2() {
        Flux.<String>generate(synchronousSink -> {
                    String country = Util.faker().country().name();
                    synchronousSink.next(country);
                })
                .takeUntil(c -> c.equalsIgnoreCase("canada")) //в  отличие от примера выше, вместо условия if (country.equalsIgnoreCase("canada")), можем просто вызвать такеАнтил
                .subscribe(Util.subscriber());
    }
}