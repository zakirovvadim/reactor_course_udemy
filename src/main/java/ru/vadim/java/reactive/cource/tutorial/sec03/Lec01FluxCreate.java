package ru.vadim.java.reactive.cource.tutorial.sec03;

import reactor.core.publisher.Flux;
import ru.vadim.java.reactive.cource.tutorial.courceUtil.Util;
import ru.vadim.java.reactive.cource.tutorial.sec03.producer.NameProducer;

// Создание флакса и помещение внутрь логики, после завершения, вызываем комплит
// это легкий пример, логику лучше выносит ьв отдельынй продьюсер NameProducer
public class Lec01FluxCreate {

    public static void main(String[] args) {

//        Flux.create(fl -> {
//            boolean flag = true;
//            while (flag) {
//                String country = Util.faker().country().name();
//                if (country.equals("Russian Federation")) {
//                    flag = false;
//                    fl.complete();
//                } else {
//                    fl.next(country);
//                }
//            }
//        }).subscribe(onNext());


        // Second variation
        NameProducer nameProducer = new NameProducer();
        Flux.create(nameProducer)
                .subscribe(Util.onNext(), Util.onError(), Util.onComplete());
//        nameProducer.produce();

        // Third - show threads. Можно потокобезопасно выдавать элементыю
        Runnable  runnable = nameProducer::produce;
        int i1 = Runtime.getRuntime().availableProcessors();
        for (int i = 0; i < 10; i++) {
            new Thread(runnable).start();
        }




    }
}
