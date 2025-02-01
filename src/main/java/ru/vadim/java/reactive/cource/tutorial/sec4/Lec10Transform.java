package ru.vadim.java.reactive.cource.tutorial.sec4;

import lombok.Data;
import reactor.core.publisher.Flux;
import ru.vadim.java.reactive.cource.tutorial.courceUtil.Util;

import java.util.function.Function;

// Трансформ помогает выделить повторяющуюся обработку в отдельный функционал (сделать общим для всех)
// и добавлять во время потока в каждой конкретной вариации работы.
public class Lec10Transform {
    public static void main(String[] args) {
        getPerson()
                .transform(applyFilterMap()) // написали отдельную обработку и вложили в оператор трансформ
                .subscribe(Util.onNext());
    }

    private static Function<Flux<Person>, Flux<Person>> applyFilterMap() {
        return flux -> flux
                .filter(person -> person.getAge() > 18)
                .doOnNext(person -> person.setName(person.getName().toUpperCase()))
                .doOnDiscard(Person.class, p -> System.out.println("Не прошедшие фильтр : " + p)); // можно добавить doOnDiscard что бы посомтреть кто не прошел фильтр
    }

    public static Flux<Person> getPerson() {
        return Flux.range(1, 10)
                .map(i -> new Person());
    }
}

@Data
class Person {
    private String name;
    private Integer age;

    public Person() {
        this.name = Util.faker().name().firstName();
        this.age = Util.faker().random().nextInt(0, 100);
    }
}