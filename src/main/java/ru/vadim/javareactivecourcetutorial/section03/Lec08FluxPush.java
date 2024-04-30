package ru.vadim.javareactivecourcetutorial.section03;

import reactor.core.publisher.Flux;
import ru.vadim.javareactivecourcetutorial.section03.producer.NameProducer;

import static ru.vadim.javareactivecourcetutorial.courceUtil.Util.*;

// Flux.push - не потокобезопасен, использовать только для одного потока
public class Lec08FluxPush {

    public static void main(String[] args) {
        NameProducer nameProducer = new NameProducer();
        Flux.push(nameProducer)
                .subscribe(onNext(), onError(), onComplete());
        Runnable  runnable = nameProducer::produce;
        for (int i = 0; i < 10; i++) {
            new Thread(runnable).start();
        }
    }
}
