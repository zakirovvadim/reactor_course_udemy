package ru.vadim.java.reactive.cource.tutorial.sec1;

import reactor.core.publisher.Mono;
import ru.vadim.java.reactive.cource.tutorial.courceUtil.Util;

public class Lec08MonoFromRunnable {
    public static void main(String[] args) {

        Mono.fromRunnable(timeConsumingProcess())
                .subscribe(Util.onNext(),
                        Util.onError(),
                        () -> {
                            System.out.println("process is done. Sending emails...");
                        });
    }

    private static Runnable timeConsumingProcess() {
        return () -> {
            try {
                System.out.println("RUn");
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Operation completed");
        };
    }
}
