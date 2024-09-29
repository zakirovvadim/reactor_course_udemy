package ru.vadim.javareactivecourcetutorial.sec4;

import reactor.core.publisher.Flux;
import ru.vadim.javareactivecourcetutorial.courceUtil.Util;

import java.util.function.Function;

import static ru.vadim.javareactivecourcetutorial.courceUtil.Util.onComplete;
import static ru.vadim.javareactivecourcetutorial.courceUtil.Util.onError;
/*
switchOnFirst - если первый элемент удовлетворяет условию, то все последующие идут выбранному условию. Если нет, то и для всех нет.
Тут в примере если возраст больше 100 у первого тогда ничего не происходит и поток идет дальше. Если нет,тогда для первого и последующих применяется
 applyFilterMap().
 */
public class Lec11SwitchOnFirst {
    public static void main(String[] args) {
        getPerson()
                .switchOnFirst((signal, personFlux) -> {
                    return signal.isOnNext() && signal.get().getAge() > 10 ? personFlux : applyFilterMap().apply(personFlux);
                })
                .subscribe(Util.onNext(), onError(), onComplete());
    }

    private static Function<Flux<Person>, Flux<Person>> applyFilterMap() {
        return flux -> flux
                .filter(person -> person.getAge() > 10)
                .doOnNext(person -> person.setName(person.getName().toUpperCase()))
                .doOnDiscard(Person.class, p -> System.out.println("Не прошедшие фильтр : " + p)); // можно добавить doOnDiscard что бы посомтреть кто не прошел фильтр
    }

    public static Flux<Person> getPerson() {
        return Flux.range(1, 10)
                .map(i -> new Person());
    }
}