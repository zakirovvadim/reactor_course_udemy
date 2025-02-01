package ru.vadim.java.reactive.cource.tutorial.sec4;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import ru.vadim.java.reactive.cource.tutorial.courceUtil.Util;
import ru.vadim.java.reactive.cource.tutorial.sec4.helper.NameGenerator;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

@Slf4j
public class NewLec03FluxCreateThreadSafety {
    public static void main(String[] args) {
        demo1();
    }

    /*
    Использование листа не потокобезопасно для добавления в нескольких потоков. вместо ожидаемого наполнения в 10 000 элементов
    мб разное количество.
     */
    private static void demo1() {
        long start = System.currentTimeMillis();
        var list = new CopyOnWriteArrayList<Integer>(); // попробовал потокобезопансую коллекцию, тоже норм работает
//        var list = new ArayList<Integer>;
        Runnable runnable = () -> {
            for (int i = 0; i < 1000; i++) {
                list.add(i);
            }
        };
        for (int i = 0; i < 100; i++) {
            Thread.ofPlatform().start(runnable);
        }
        long finish = System.currentTimeMillis();
        Util.sleepSeconds(60);
        log.info("list size: {}, time: {}", list.size(), finish - start);
    }

    // FLux потокобезопасен
    private static void demo2() {
        long start = System.currentTimeMillis();
        ArrayList<String> list = new ArrayList<>();
        NameGenerator generator = new NameGenerator();
        Flux<String> stringFlux = Flux.create(generator);
        stringFlux.subscribe(list::add);
        Runnable runnable = () -> {
            for (int i = 0; i < 1000; i++) {
                generator.generate();
            }
        };
        for (int i = 0; i < 100; i++) {
            Thread.ofPlatform().start(runnable);
        }
        long finish = System.currentTimeMillis();
        Util.sleepSeconds(3);
        log.info("list size: {}, time: {}", list.size(), finish - start);
    }

}
