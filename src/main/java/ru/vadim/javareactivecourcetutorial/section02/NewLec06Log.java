package ru.vadim.javareactivecourcetutorial.section02;

import com.github.javafaker.Faker;
import reactor.core.publisher.Flux;
import ru.vadim.javareactivecourcetutorial.courceUtil.Util;

public class NewLec06Log {
    public static void main(String[] args) {
        Flux.range(1, 5)
                .log()
                .map(i -> Faker.instance().address().cityName())
                .log()
                .subscribe(Util.subscriber());
    }
}
