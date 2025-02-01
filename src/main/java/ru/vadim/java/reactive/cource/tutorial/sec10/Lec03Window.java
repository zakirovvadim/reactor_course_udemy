package ru.vadim.java.reactive.cource.tutorial.sec10;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
/*
window() - оператор похож на баффер, но если баффер буфферизует поток эллементов в коллекцию, то виндов - создаети новый
продьюсер куда перенаправляет данные. Соответсвенно после виндов нужно использовать флетМап но соовтетсвующим потребителем
нового потока. Пример испольхования, это запись логов в документ, в виндоу мы указываем периодичность количеством элементов
или времени, и с окнчанием периода используем новый консьюмер тобишь файл, куда будут записаны данные.
Вывод в консоль пример следующий (5 звезд, потимоу что пять было аргументов в операторе виндоу.):
*****
*****
*****
*****
 */
public class Lec03Window {
    public static void main(String[] args) throws InterruptedException {

        eventStream()
                .window(5)
                .flatMap(flx -> processEvents(flx))
                .subscribe();

        Thread.sleep(Duration.ofSeconds(60));
    }

    private static Flux<String> eventStream() {
        return Flux.interval(Duration.ofMillis(500))
                .map(el -> "element-" + (el + 1));
    }

    private static Mono<Void> processEvents(Flux<String> flux) {
        return flux
                .doOnNext(el -> System.out.print("*"))
                .doOnComplete(System.out::println)
                .then();
    }
}
