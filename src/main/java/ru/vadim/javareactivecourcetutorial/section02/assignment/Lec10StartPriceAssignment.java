package ru.vadim.javareactivecourcetutorial.section02.assignment;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;
import java.util.concurrent.CountDownLatch;

public class Lec10StartPriceAssignment {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Flux.range()
        StockPricePubsher.getPrice()
                .subscribeWith(new Subscriber<Integer>() {
                    Subscription sub;
                    @Override
                    public void onSubscribe(Subscription subscription) {
                        this.sub = subscription;
                        sub.request(Long.MAX_VALUE);
                    }

                    @Override
                    public void onNext(Integer integer) {
                        System.out.println(LocalDateTime.now() + " Price : " + integer);
                        if (integer > 110 || integer < 90) {
                            this.sub.cancel();
                            latch.countDown();
                        }
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        latch.countDown();
                    }

                    @Override
                    public void onComplete() {
                        latch.countDown();
                    }
                });
        latch.await();
    }
}
