package ru.vadim.updatedCource.sec05;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.vadim.java.reactive.cource.tutorial.courceUtil.Util;

/*
Нужно помнить, что отливлиание ошибки происходит после производителя или оператора непосредственно, как источника.
 */
@Slf4j
public class Lec06ErrorHandling {
    public static void main(String[] args) {

    }

    // Если не смотря на ошибку, хотим продолжить потребление данных
    private static void onErrorContinue() {
        Flux.range(1, 10)
                .map(i -> i == 5 ? 5 / 0 : i)
                .onErrorContinue((ex, obj) -> log.error("==> {}",obj, ex))
                .subscribe(Util.subscriber());
    }

    /*Если не нужно отлавливание и запасныеварианты. Просто дайте значение или завершение.
    Здесь нет никаких проблем, никаких вызовов.
    Но в случае ошибки он скроется и будет выглядеть так, будто вы получаете полный сигнал
    */
    private static void onErrorComplete() {
        Mono.just(1)
                .onErrorComplete()
                .subscribe(Util.subscriber());
    }


    // когда мы хотим захардкодить значение ошибки
    private static void onErrorReturn() {
        Flux.range(1, 10)
                //.onErrorReturn(-1) это не выдаст ошибку, так как range-производитель не выбрасывает ее
                .map(i -> i == 5 ? 5 / 0 : i)
                .onErrorReturn(IllegalArgumentException.class, 400) // метод для отлавливания конкретного эксепшена.
                .onErrorReturn(ArithmeticException.class, 500)
                .onErrorReturn(-1) // отлавливает любую ошибку без конркетики
                .subscribe(Util.subscriber());
    }

    //если хочешь вернуть другого производителя в случае ошибки
    private static void onErrorResume() {
        Mono.error(new RuntimeException("oops"))
                .onErrorResume(ArithmeticException.class, ex -> fallback()) // ex -> fallback() - запасной вариант, в случае падения именно арифметик эксепшн
                .onErrorResume(ex -> fallback2()) // второй запасной вариант, запасных вариантов может быть сколько угодно
                .onErrorReturn(-1) // хендлер ошибки по умолчанию
                .subscribe(Util.subscriber());
    }

    // запасные варианты, мок другого сервиса
    private static Mono<Integer> fallback() {
        return Mono.fromSupplier(() -> Util.faker().random().nextInt(10, 100));
    }

    // запасные варианты, мок другого сервиса
    private static Mono<Integer> fallback2() {
        return Mono.fromSupplier(() -> Util.faker().random().nextInt(10, 100));
    }
}
