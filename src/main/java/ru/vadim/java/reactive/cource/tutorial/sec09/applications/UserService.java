package ru.vadim.java.reactive.cource.tutorial.sec09.applications;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

public class UserService {

    public static final Map<String, Integer> userTable = Map.of(
            "vadim", 1,
            "nastya", 2,
            "darja", 3
    );

    public static Flux<User> getAllUsers() {
        return Flux.fromIterable(userTable.entrySet())
                .map(entry -> new User(entry.getValue(), entry.getKey()));
    }

    public static Mono<Integer> getuserId(String userName) {
        return Mono.fromSupplier(() -> userTable.get(userName));
    }

}
