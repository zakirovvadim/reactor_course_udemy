package ru.vadim.java.reactive.cource.tutorial.sec03.producer;

import reactor.core.publisher.FluxSink;
import ru.vadim.java.reactive.cource.tutorial.courceUtil.Util;

import java.util.function.Consumer;

public class NameProducer implements Consumer<FluxSink<String>> {

    private FluxSink<String> stringFluxSink;

    @Override
    public void accept(FluxSink<String> stringFluxSink) {
        this.stringFluxSink = stringFluxSink;
    }

    public void produce() {
        String name = Util.faker().name().fullName();
        String thread = Thread.currentThread().getName();
        this.stringFluxSink.next(thread + " : " + name);
    }
}
