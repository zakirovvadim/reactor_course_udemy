package ru.vadim.java.reactive.cource.tutorial.sec1;

import lombok.extern.slf4j.Slf4j;
import ru.vadim.java.reactive.cource.tutorial.courceUtil.Util;
import ru.vadim.java.reactive.cource.tutorial.sec02.client.ExternalServiceClient;

@Slf4j
public class Lec11NonBlockingIO {

    public static void main(String[] args) {
        var client = new ExternalServiceClient();
        log.info("starting");

        for (int i = 0; i <= 1000; i++) {
            client.getProductName(i)
                    .subscribe(Util.subscriber());
        }
        Util.sleepSeconds(2);
    }
}
