package ru.vadim.javareactivecourcetutorial.sec13;

import reactor.util.context.Context;
import ru.vadim.javareactivecourcetutorial.courceUtil.Util;
import ru.vadim.javareactivecourcetutorial.sec13.client.ExternalServiceClient;

public class Lec04ContextRateLimiterDemo {
    public static void main(String[] args) {
        ExternalServiceClient client = new ExternalServiceClient();
        for (int i = 0; i < 20; i++) {
            client.getBook()
                    .contextWrite(Context.of("user", "vadim"))
                    .subscribe(Util.subscriber());
            Util.sleepSeconds(1);
        }
        Util.sleepSeconds(5);
    }
}
