package ru.vadim.javareactivecourcetutorial.section4;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import ru.vadim.updatedCource.Lection01.subscriber.SubscriberImpl;
import ru.vadim.updatedCource.common.Util;
/*
Part 1
При создании флакса он заранее создает элементы, даже если нет подписчика. Что мы можем увидеть, если запустим лог внутри
флаксСинка. Да, это часть не лейзи, надо быть осторожным, так как создание идет в очередь без огранчиений и может упасть по памяти.

Part 2
Если нам не нужно чтобы флакс создавал сразу все элементы, мы можем использовать у флаксСинка метод онРеквест, т.е. по требованию.
Шде в условие создания доабвляем переменную консьюмера - request, что означает количество создаваемых переменных.
 */
@Slf4j
public class NewLec04FluxCreateDownstreamDemand {

    public static void main(String[] args) throws InterruptedException {
        productOnDemand();
    }

    private static void getAll() {
        SubscriberImpl subscriber = new SubscriberImpl();
        Flux.<String>create(stringFluxSink -> {
            for (int i = 0; i < 10; i++) {
                var name = Util.faker().name().firstName();
                log.info("generated: {}", name);
                stringFluxSink.next(name);
            }
            stringFluxSink.complete();
        }).subscribe(subscriber);
    }

    private static void productOnDemand() throws InterruptedException {
        SubscriberImpl subscriber = new SubscriberImpl();
        Flux.<String>create(fluxSink -> {
            fluxSink.onRequest(request -> {
                for (int i = 0; i < request && !fluxSink.isCancelled(); i++) {
                    var name = Util.faker().name().firstName();
                    log.info("generated: {}", name);
                    fluxSink.next(name);
                }
            });
        }).subscribe(subscriber);
        Thread.sleep(1000);
        subscriber.getSubscription().request(10);

        Thread.sleep(1000);
        subscriber.getSubscription().request(10);

        Thread.sleep(1000);
        subscriber.getSubscription().cancel();
        subscriber.getSubscription().request(2);
    }
}
