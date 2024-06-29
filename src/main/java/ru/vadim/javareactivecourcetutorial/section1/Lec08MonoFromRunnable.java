package ru.vadim.javareactivecourcetutorial.section1;

import reactor.core.publisher.Mono;

import static ru.vadim.javareactivecourcetutorial.courceUtil.Util.onError;
import static ru.vadim.javareactivecourcetutorial.courceUtil.Util.onNext;

public class Lec08MonoFromRunnable {
    public static void main(String[] args) {

        Mono.fromRunnable(timeConsumingProcess())
                .subscribe(onNext(),
                        onError(),
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
