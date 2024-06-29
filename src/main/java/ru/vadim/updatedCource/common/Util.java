package ru.vadim.updatedCource.common;

import org.reactivestreams.Subscriber;
import reactor.core.publisher.Mono;

public class Util {

    public static <T> Subscriber<T> subscriber() {
        return new DefaultSubscriberImpl<>("");
    }

    public static <T> Subscriber<T> subscriber(String name) {
        return new DefaultSubscriberImpl<>(name);
    }
}
