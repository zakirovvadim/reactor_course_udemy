package ru.vadim.javareactivecourcetutorial.sec09;


import reactor.core.publisher.Mono;
import ru.vadim.javareactivecourcetutorial.section02.client.ExternalServiceClient;
import ru.vadim.updatedCource.common.Util;

import java.time.Duration;

public class Lec08ZipAssingment {
    
    private record Product(String price, String product, String review){}

    public static void main(String[] args) throws InterruptedException {
        ExternalServiceClient service = new ExternalServiceClient();
        for (int i = 0; i < 10; i++) {
            service.getProduct(i)
                    .subscribe(Util.subscriber());
        }
        Thread.sleep(Duration.ofSeconds(5));
    }
}
