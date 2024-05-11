package ru.vadim.javareactivecourcetutorial.section4;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.vadim.javareactivecourcetutorial.courceUtil.Util;

import static ru.vadim.javareactivecourcetutorial.courceUtil.Util.onComplete;
import static ru.vadim.javareactivecourcetutorial.courceUtil.Util.onError;

public class Lec06OnError {

    public static void main(String[] args) {
        Flux.range(1,10)
                .log()
                .map(i -> 10 / (5 - i))
                //.onErrorReturn(-1) //просто заменяет объект в случае ошибки на какой то простой объект типа <T> и останавливает поток
//                .onErrorResume( e -> fallback()) // в случае ошибки останавливает поток и вовзращает кастомный возврат
                .onErrorContinue((err, obj) -> { // продолжает поток в случае ошибки
                    System.out.println(err.getMessage()); // здесь можно зарегистрировать и логгировать ошибку
                })
                .subscribe(Util.onNext(), onError(), onComplete());
    }

    private static Mono<Integer> fallback() {
        return Mono.fromSupplier(() -> Util.faker().random().nextInt(100, 200));
    }
}
