package ru.vadim.java.reactive.cource.tutorial.sec10.assingment;

import com.github.javafaker.Book;
import reactor.core.publisher.Flux;
import ru.vadim.updatedCource.common.Util;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static reactor.core.publisher.Flux.generate;

public class AssingmentBook {
    public static void main(String[] args) throws InterruptedException {
        Set<String> allowedGenre = Set.of("Science fiction", "Fantasy", "Suspence/Thriller");
        getBookOrders()
                .filter(o -> allowedGenre.contains(o.genre))
                .buffer(Duration.ofSeconds(5))
                .map(AssingmentBook::calculateRevenue)
                .subscribe(Util.subscriber());

        Thread.sleep(Duration.ofSeconds(60));
    }

    private static Flux<BookOrder> getBookOrders() {
        return Flux.generate(synchronousSink -> {
                    Book book = Util.faker().book();
                    BookOrder bookOrder = new BookOrder(book.genre(), book.title(), Util.faker().random().nextInt(10, 100));
                    synchronousSink.next(bookOrder);
                })
                .cast(BookOrder.class)
                .delayElements(Duration.ofMillis(200));

    }

    private static RevenueReport calculateRevenue(List<BookOrder> books) {
        Map<String, Integer> collect = books.stream()
                .collect(Collectors.groupingBy(BookOrder::genre, Collectors.summingInt(BookOrder::price)));
        return new RevenueReport(LocalDate.now(), collect);
    }

    private record BookOrder(String genre, String title, Integer price) {}
    private record RevenueReport(LocalDate date, Map<String, Integer> books){};
}
