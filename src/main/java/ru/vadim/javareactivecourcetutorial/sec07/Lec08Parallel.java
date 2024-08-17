package ru.vadim.javareactivecourcetutorial.sec07;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;
import ru.vadim.javareactivecourcetutorial.courceUtil.Util;

import java.time.Duration;

@Slf4j
public class Lec08Parallel {
    public static void main(String[] args) throws InterruptedException {
        Flux.range(1, 100)
                .parallel()
                .runOn(Schedulers.parallel())
                .map(Lec08Parallel::process)
                .sequential()
                .map(i -> i + "This is IIIII")
                .subscribe(Util.subscriber());

        Thread.sleep(Duration.ofSeconds(120));
    }

    private static double process(int i) {
        log.info("Time consuming task {}", i);
        Util.sleepSeconds(1);
        return i * Math.random();
    }
}
