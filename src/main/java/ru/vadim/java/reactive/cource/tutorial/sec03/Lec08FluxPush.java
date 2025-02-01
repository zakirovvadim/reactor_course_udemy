package ru.vadim.java.reactive.cource.tutorial.sec03;

import reactor.core.publisher.Flux;
import ru.vadim.java.reactive.cource.tutorial.courceUtil.Util;
import ru.vadim.java.reactive.cource.tutorial.sec03.producer.NameProducer;

// Flux.push - не потокобезопасен, использовать только для одного потока
public class Lec08FluxPush {

    public static void main(String[] args) {
        NameProducer nameProducer = new NameProducer();
        Flux.push(nameProducer)
                .subscribe(Util.onNext(), Util.onError(), Util.onComplete());
        Runnable  runnable = nameProducer::produce;
        for (int i = 0; i < 10; i++) {
            new Thread(runnable).start();
        }
    }
}
