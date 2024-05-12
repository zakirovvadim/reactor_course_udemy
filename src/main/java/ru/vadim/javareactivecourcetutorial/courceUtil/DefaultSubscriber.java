package ru.vadim.javareactivecourcetutorial.courceUtil;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class DefaultSubscriber implements Subscriber<Object> {

    private String name;

    @Override
    public void onSubscribe(Subscription subscription) {
        subscription.request(Long.MAX_VALUE);
    }

    @Override
    public void onNext(Object o) {
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
