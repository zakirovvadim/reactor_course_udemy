package ru.vadim.javareactivecourcetutorial.section1;

import lombok.extern.slf4j.Slf4j;
import ru.vadim.javareactivecourcetutorial.courceUtil.Util;
import ru.vadim.javareactivecourcetutorial.section02.client.ExternalServiceClient;

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
