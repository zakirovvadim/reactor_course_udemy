package ru.vadim.javareactivecourcetutorial.section02.assignment;

import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;
import ru.vadim.javareactivecourcetutorial.courceUtil.Util;
import ru.vadim.javareactivecourcetutorial.section02.client.ExternalServiceClient;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

import static ru.vadim.javareactivecourcetutorial.courceUtil.Util.*;

@Slf4j
public class NewStockTask {

    private static int balance = 1000;
    private static int numberOfStocks = 0;;

    public static void main(String[] args) {
        buyCurrency();
        System.out.println("balance: " + balance + " quantity: " + numberOfStocks);
    }

    private static void buyCurrency() {
        ExternalServiceClient client = new ExternalServiceClient();
        client.getCurrency()
                .map(Integer::parseInt)
                .subscribe(new Subscriber<Integer>() {
                    private Subscription subscription;
                    @Override
                    public void onSubscribe(Subscription subscription) {
                        this.subscription = subscription;
                        subscription.request(Long.MAX_VALUE);
                    }

                    @Override
                    public void onNext(Integer integer) {
                        log.info("Price : " + integer);
                        if (integer <= 90) {
                            int numberForBuy = balance / integer;
                            numberOfStocks = numberOfStocks + numberForBuy;
                            balance = balance - (numberForBuy * integer);
                            log.info("Price : " + integer + " balance: " + balance);
                        } else if (integer >= 110) {
                            int priceForSelling = numberOfStocks * integer;
                            numberOfStocks = 0;
                            balance = balance + priceForSelling;
                            subscription.cancel();
                        }
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        log.info("OnError countDown");
                    }

                    @Override
                    public void onComplete() {
                        log.info("OnComplete countDown");
                    }
                });

        Util.sleepSeconds(20);
    }
}
