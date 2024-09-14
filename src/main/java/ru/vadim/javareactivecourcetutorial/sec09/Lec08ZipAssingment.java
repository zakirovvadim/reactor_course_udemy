package ru.vadim.javareactivecourcetutorial.sec09;


import reactor.core.publisher.Mono;
import ru.vadim.javareactivecourcetutorial.section02.client.ExternalServiceClient;
import ru.vadim.updatedCource.common.Util;

import java.time.Duration;

public class Lec08ZipAssingment {
    
    private record Product(String price, String product, String review){}

    public static void main(String[] args) throws InterruptedException {
        ExternalServiceClient service = new ExternalServiceClient();
        service.demo05GetPrice(1).subscribe(Util.subscriber());
        for (int i = 0; i < 10; i++) {
            Mono.zip(service.demo05GetPrice(i), service.demo05GetProduct(i), service.demo05GetReview(i))
//                    .map(el -> )
                    .subscribe(Util.subscriber());
        }

        Thread.sleep(Duration.ofSeconds(5));
    }
}
