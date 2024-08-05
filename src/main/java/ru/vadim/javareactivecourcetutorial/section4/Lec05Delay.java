package ru.vadim.javareactivecourcetutorial.section4;

import reactor.core.publisher.Flux;
import reactor.util.concurrent.Queues;
import ru.vadim.javareactivecourcetutorial.courceUtil.Util;

import java.time.Duration;

import static ru.vadim.javareactivecourcetutorial.courceUtil.Util.onNext;
/*
Когда мы устанавливаем задержку, напимер в 2 секунды это не значит,что продьюсер заполняет данными память чтобы потом выдать каждый промежуток
Вметсо этого реквест сам запрашивает дынне по 1 штуке каждый утсановленным промежуток времени.Поэтому продюсер не делает всю работу заранее.
И делает это только тогда, когда это необходимо.
 */
public class Lec05Delay {

    public static void main(String[] args) throws InterruptedException {
        System.setProperty("reactor.bufferSize.x", "9"); // default buffer 32
        Flux.range(1, 100)
                .log()
                .delayElements(Duration.ofSeconds(2))
                .subscribe(onNext());

        Thread.sleep(60000);
    }
}
