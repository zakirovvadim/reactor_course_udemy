package ru.vadim.javareactivecourcetutorial.courceUtil;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.reactivestreams.Subscriber;

import java.util.concurrent.Flow;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class DeafultSubscriber implements Flow.Subscriber {

    private String name = "";


    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        subscription.request(Long.MAX_VALUE);
    }

    @Override
    public void onNext(Object item) {
        System.out.println(name + " - Received : " + item);
    }

    @Override
    public void onError(Throwable throwable) {
        System.out.println(name + " - ERROR : " + throwable.getMessage());
    }

    @Override
    public void onComplete() {
        System.out.println(name + " - Completed : ");
    }

    public static Flow.Subscriber<Object> subscriber(String name) {
        return new DeafultSubscriber(name);
    }
}
