package ru.vadim.updatedCource.sec05.assingment;

import java.time.Duration;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        ProductService productService = new ProductService();
        for (int i = 0; i < 5; i++) {
            productService.extractProductService(i);
        }
        Thread.sleep(Duration.ofSeconds(5));
    }
}
