package ru.vadim.javareactivecourcetutorial.section1;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import ru.vadim.javareactivecourcetutorial.courceUtil.Util;

import java.util.List;
/*
Mono.defer() позволяет откладывать создание издателя. Вообще создание издателя должно быть легковесной операцией. Однаков в нашем методе
createPublisher() это может занять минуту если мы подпишемся на моно, но defer(), позволяет отложить создание паблишера.
 */

@Slf4j
public class Lec10MonoDefer {

    public static void main(String[] args) {
        Mono.defer(() -> createPublisher());
//        .subscriber(Util.subscriber());
    }

    private static Mono<Integer> createPublisher() {
        log.info("creating publisher");
        var list = List.of(1,2,3);
        Util.sleepSeconds(1);
        return Mono.fromSupplier(() -> sum(list));
    }

    private static int sum(List<Integer> list) {
        log.info("finding the sum of {}", list);
        Util.sleepSeconds(3);
        return list.stream().mapToInt(a -> a).sum();
    }
}
