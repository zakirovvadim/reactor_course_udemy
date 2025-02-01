package ru.vadim.updatedCource.sec05.assingment;

import ru.vadim.java.reactive.cource.tutorial.courceUtil.Util;
import ru.vadim.java.reactive.cource.tutorial.sec02.client.ExternalServiceClient;

import java.time.Duration;

public class ProductService {

    public void extractProductService(Integer id) {
        ExternalServiceClient client = new ExternalServiceClient();
        client.getProductService(id)
                .timeout(Duration.ofSeconds(2), client.getTimeoutFallbackProduct(id))
                .switchIfEmpty(client.getEmptyFallbackProduct(id))
                .subscribe(Util.subscriber());
    }
}
