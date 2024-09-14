package ru.vadim.javareactivecourcetutorial.sec09;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.vadim.updatedCource.common.Util;

import java.time.Duration;
import java.util.List;
/*
then() используется, если нам нужно выполнить следующее действие с полученными данными. Например, при сохранении нам может не потребуется
получать результат сохранения, и вместо этого мы можем послать уведомление о полном завершении, тогда и может пригодится этот оператор.
В случае ошибки, ошибка выдаст нам ее, то есть выдаст нам успех или неудачу.
 */
@Slf4j
public class Lec15Then {
    public static void main(String[] args) throws InterruptedException {
        var records = List.of("a", "b", "m");
        saveRecords(records)
                .then(sendNotifications(records))
                .subscribe(Util.subscriber());
        Thread.sleep(5000);
    }

    private static Flux<String> saveRecords(List<String> records) {
        return Flux.fromIterable(records)
                .map(r -> "saved " + r)
                .delayElements(Duration.ofMillis(500));
    }

    private static Mono<Void> sendNotifications(List<String> records) {
        return Mono.fromRunnable(() -> log.info("all these {} records saved successfully", records));
    }
}
