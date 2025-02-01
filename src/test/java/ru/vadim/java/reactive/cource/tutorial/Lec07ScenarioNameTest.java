package ru.vadim.java.reactive.cource.tutorial;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import reactor.test.StepVerifierOptions;

public class Lec07ScenarioNameTest {

    public Flux<Integer> getItems() {
        return Flux.range(1, 3);
    }

    /*
    Можно добавитбь описание к тестам через степ верифаер опшнс
     */

    @Test
    public void scenarioNameTest() {
        var options = StepVerifierOptions.create().scenarioName("1 to 3 items test");
        StepVerifier.create(getItems(), options)
                .expectNext(1)
                .as("first item should be 1")
                .expectNext(2, 3)
                .as("then 2 and 3")
                .expectComplete()
                .verify();
    }
}
