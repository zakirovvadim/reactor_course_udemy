package ru.vadim.java.reactive.cource.tutorial;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

/*
verifyError(), verifyComplete() - более удобные варианты expectError и т.д.
 */
public class Lec02EmptyErrorTest {

    Mono<String> getUsername(int userId) {
        return switch ( userId) {
            case 1 -> Mono.just("sam");
            case 2 -> Mono.empty();
            default -> Mono.error(new RuntimeException("invalid input"));
        };
    }
    @Test
    public void userTest() {
        StepVerifier.create(getUsername(1))
                .expectNext("sam")
                .expectComplete()
                .verify();
    }

    @Test
    public void emptyTest() {
        StepVerifier.create(getUsername(2))
                .expectComplete()
                .verify();
    }

    @Test
    public void errorTest1() {
        StepVerifier.create(getUsername(3))
                .expectError()
                .verify();
    }

    @Test
    public void errorTest2SpecificException() {
        StepVerifier.create(getUsername(3))
                .expectError(RuntimeException.class)
                .verify();
    }

    @Test
    public void errorTestExpectErrorMessage() {
        StepVerifier.create(getUsername(3))
                .expectErrorMessage("invalid input")
                .verify();
    }

    @Test
    public void errorTestMoreDetailEquals() {
        StepVerifier.create(getUsername(3))
//                .consumeNextWith()
                .consumeErrorWith(ex -> {
                    Assertions.assertEquals(RuntimeException.class, ex.getClass());
                    Assertions.assertEquals("invalid input", ex.getMessage());
                })
                .verify();
    }
}
