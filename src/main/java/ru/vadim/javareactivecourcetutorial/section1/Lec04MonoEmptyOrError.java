package ru.vadim.javareactivecourcetutorial.section1;

import reactor.core.publisher.Mono;
import ru.vadim.javareactivecourcetutorial.courceUtil.Util;

import static ru.vadim.javareactivecourcetutorial.courceUtil.Util.*;

public class Lec04MonoEmptyOrError {
    public static void main(String[] args) {
        userRepository(3)
                .subscribe(
                        onNext(),
                        onError(),
                        onComplete()
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
