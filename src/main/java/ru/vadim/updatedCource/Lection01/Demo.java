package ru.vadim.updatedCource.Lection01;

import ru.vadim.updatedCource.Lection01.publisher.PublisherImpl;
import ru.vadim.updatedCource.Lection01.subscriber.SubscriberImpl;

import java.time.Duration;

public class Demo {
    public static void main(String[] args) throws InterruptedException {
        demo2();
    }

    private static void demo1() {
        var publisher = new PublisherImpl();
        var subscriber = new SubscriberImpl();
        publisher.subscribe(subscriber);
    }

    private static void demo2() throws InterruptedException {
        var publisher = new PublisherImpl();
        var subscriber = new SubscriberImpl();
        publisher.subscribe(subscriber);
        subscriber.getSubscription().request(3);
        Thread.sleep(2000);
        System.out.println(1);
        subscriber.getSubscription().request(3);
        Thread.sleep(2000);
        System.out.println(2);
        subscriber.getSubscription().request(3);
        Thread.sleep(2000);
        System.out.println(3);
        subscriber.getSubscription().request(3);
        Thread.sleep(2000);
        System.out.println(4);
        subscriber.getSubscription().request(3);
    }
}
