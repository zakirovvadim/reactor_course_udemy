package ru.vadim.java.reactive.cource.tutorial.sec1;

import java.util.stream.Stream;

public class Lec01Stream {

    public static void main(String[] args) {
        Stream<Integer> stream =  Stream.of(1)
                .map(i -> {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    return i * 2;
                });

        System.out.println(stream);
    }
}
