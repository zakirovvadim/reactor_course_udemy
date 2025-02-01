package ru.vadim.java.reactive.cource.tutorial;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import ru.vadim.java.reactive.cource.tutorial.courceUtil.Util;

import java.util.Objects;

public class Lec05AssertNextTest {
    record Book(int id, String author, String title){}

    private Flux<Book> getBooks() {
        return Flux.range(1, 3)
                .map(i -> new Book(i, Util.faker().book().author(), Util.faker().book().title()));
    }

    @Test
    public void assertNextTest() {
        StepVerifier.create(getBooks())
                .assertNext(b -> Assertions.assertEquals(1, b.id)) // проверяем только 1 элемент
                .thenConsumeWhile(b -> Objects.nonNull(b.title))// дальше у всех првоеряем только на не налл
                .expectComplete()
                .verify();
    }

    @Test
    public void collectAllAndTest() {
        StepVerifier.create(getBooks().collectList())
                .assertNext(list -> Assertions.assertEquals(3, list.size()))
                .expectComplete()
                .verify();
    }
}
