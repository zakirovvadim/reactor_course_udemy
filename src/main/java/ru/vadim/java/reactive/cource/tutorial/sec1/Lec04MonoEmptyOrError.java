package ru.vadim.java.reactive.cource.tutorial.sec1;

import reactor.core.publisher.Mono;
import ru.vadim.java.reactive.cource.tutorial.courceUtil.Util;

public class Lec04MonoEmptyOrError {
    public static void main(String[] args) {
        userRepository(3)
                .subscribe(
                        Util.onNext(),
                        Util.onError(),
                        Util.onComplete()
                );
    }

    private static Mono<String> userRepository(int userId) {
        if (userId == 1) {
            return Mono.just(Util.faker().name().firstName());
        } else if (userId == 2) {
            return Mono.empty(); //instead of null
        } else {
            return Mono.error(new RuntimeException("Not in the allowed range"));
        }
    }
}
