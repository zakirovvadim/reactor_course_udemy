package ru.vadim.javareactivecourcetutorial.sec02;

import com.github.javafaker.Faker;
import reactor.core.publisher.Flux;
import ru.vadim.javareactivecourcetutorial.courceUtil.Util;

public class NewLec05FluxFromRange {
    public static void main(String[] args) {
        Flux.range(1, 100)
                .map(el -> Faker.instance().name().firstName())
                .subscribe(Util.subscriber());
    }
}
