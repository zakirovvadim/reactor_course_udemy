package ru.vadim.javareactivecourcetutorial.sec10;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.GroupedFlux;
import reactor.core.publisher.Mono;

import java.time.Duration;
/*
groupBy позволяет группировать получаемые элементы в отдельные флакс паблишеры по ключам. Отличие от виндов том, что виндоу
сразу же закрывает флакс после установленного условия, а паблишер держит открытым.
Нужно быть осторожным и использовать только с низкой кардинальностью, т е небольшим количеством униклаьных ключей.
Так же если по ключу создается флакс, а в дальнейшем по этому ключу элементы не будут приходить, то сгруппированный поток так и останется открытым.
(конечно если общий производитель закончит публикацию то все сгруппированные потоки закроются, но надо иметь это ввиду)
 */
@Slf4j
public class Lec05GroupFlux {
    public static void main(String[] args) throws InterruptedException {
        Flux.range(1, 30)
                .delayElements(Duration.ofMillis(1000))
                .map(i -> i * 2)
                .startWith(1)
                .groupBy(i -> i % 2)
                .flatMap(Lec05GroupFlux::processFlux)
                .subscribe();

        Thread.sleep(Duration.ofSeconds(35));
    }

    private static Mono<Void> processFlux(GroupedFlux<Integer, Integer> flux) {
        log.info("Start processFlux key: ", flux.key());
        return flux
                .doOnNext(i -> log.info("key: '{}', value: '{}'", flux.key(), i))
                .doOnComplete(() -> log.info("{} completed", flux.key()))
                .then();
    }
}
