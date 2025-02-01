package ru.vadim.java.reactive.cource.tutorial.sec09;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import ru.vadim.java.reactive.cource.tutorial.courceUtil.Util;

import java.time.Duration;
import java.util.List;

// В startWith работает при восходящем потоке.
@Slf4j
public class Lec01StartWith {
    public static void main(String[] args) throws InterruptedException {
        demo4();
        demo3();

        Thread.sleep(Duration.ofSeconds(15));
    }

    public static void demo1() {
        /*
        Перед потреблением проьюсера мы можем начать с тех значений(данных) которые хотим, а потом уже идет потребление с подписки.
         */
        producer1()
                .startWith(-1, 0)
//                .take(2) если сделаем тейк он заберет данные в том числе из startWith, и в данном случае потребит два числа -1 и 0 и не будет подписываться к продьюсеру
                .subscribe(Util.subscriber());
    }

    public static void demo2() {
        // Можно добавить коллекцию
        producer1()
                .startWith(List.of(-2, -1, 0))
                .subscribe(Util.subscriber());
    }

    public static void demo3() {
        //Можно положить в качестве аргумента второй продьюсер. И потребление начентся с него
        producer1()
                .startWith(producer2())
                .subscribe(Util.subscriber());
    }

    public static void demo4() {
        // Можно использовать несколько операторов стартВиз, работают также по восходящему потоку
        producer1()
                .startWith(producer2())
                .startWith(1000)
                .subscribe(Util.subscriber());
        producer2()
                .startWith(producer1())
                .startWith(100)
                .subscribe(Util.subscriber())
        ;
    }

    private static Flux<Integer> producer1() {
        return Flux.just(1, 2, 3)
                .doOnSubscribe(s -> log.info("subscribing to producer1"))
                .delayElements(Duration.ofMillis(10));
    }

    private static Flux<Integer> producer2() {
        return Flux.just(51, 52, 53)
                .doOnSubscribe(s -> log.info("subscribing to producer2"))
                .delayElements(Duration.ofMillis(10));
    }
}
