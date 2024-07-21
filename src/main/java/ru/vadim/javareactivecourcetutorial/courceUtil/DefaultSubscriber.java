package ru.vadim.javareactivecourcetutorial.courceUtil;

import lombok.Getter;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

@Getter
public class DefaultSubscriber<T> implements Subscriber<T> {

    private String name;

    public DefaultSubscriber() {
    }

    public DefaultSubscriber(String name) {
        this.name = name;
    }

    @Override
    public void onSubscribe(Subscription subscription) {
        subscription.request(Long.MAX_VALUE);
    }

    @Override
    public void onNext(T o) {
        System.out.println(name + " - Received " + o);
    }

    @Override
    public void onError(Throwable throwable) {
        System.out.println(name + " - ERROR : " + throwable.getMessage());
    }

    @Override
    public void onComplete() {
        System.out.println(name + " - Completed");
    }
}
