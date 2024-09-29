package ru.vadim.javareactivecourcetutorial.sec02;

import reactor.core.publisher.Flux;

import java.util.List;

import static ru.vadim.javareactivecourcetutorial.courceUtil.Util.*;

public class Lec03FluxFromArrayList {

    public static void main(String[] args) {
        List<String> list = List.of("a", "b", "c", "d");

        Flux.fromIterable(list)
                .subscribe(onNext(), onError(), onComplete());

        Integer [] array = {1,2,3,4,5,6};
        Flux.fromArray(array)
                .subscribe(onNext());
    }
}
