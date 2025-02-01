package ru.vadim.java.reactive.cource.tutorial.sec1;
import reactor.core.publisher.Mono;
public class Lec02MonoJust {

    public static void main(String[] args) {
        //publisher
        Mono<Integer> just = Mono.just( 1);

        System.out.println(just);
        just.subscribe( i -> System.out.println("Received : " + 1));
    }
}
