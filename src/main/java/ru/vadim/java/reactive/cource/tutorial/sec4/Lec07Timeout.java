package ru.vadim.java.reactive.cource.tutorial.sec4;

import reactor.core.publisher.Flux;
import ru.vadim.java.reactive.cource.tutorial.courceUtil.Util;

import java.time.Duration;

public class Lec07Timeout {
    public static void main(String[] args) throws InterruptedException {

        getOrderNumbers()
                .timeout(Duration.ofSeconds(2), fallback()) // если в течение 2 секунд не пойдут данные, переходит на запасной метод fallback()
                .subscribe(Util.onNext());

        Thread.sleep(6000);
    }
    private static Flux<Integer> getOrderNumbers() {
        return Flux.range(1, 10)
                .delayElements(Duration.ofSeconds(1)); // промежуток выдачи, до первого элемента также ждем установленное время
    }
    private static Flux<Integer> fallback() {
        return Flux.range(100, 10)
                .delayElements(Duration.ofMillis(200));
    }
}
