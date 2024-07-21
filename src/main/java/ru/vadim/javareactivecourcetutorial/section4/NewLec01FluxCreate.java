package ru.vadim.javareactivecourcetutorial.section4;

import reactor.core.publisher.Flux;
import ru.vadim.javareactivecourcetutorial.courceUtil.Util;

/*
fluxSink - позволяет использовать циклы внутри флакса при его создании. И завершать после определенного условия, будто окончание
цикла или иного кастомного условиякак как иквалс на страну Канада во втором примере.
 */
public class NewLec01FluxCreate {
    public static void main(String[] args) {
//        Flux.create(fluxSink -> {
//            for (int i = 0; i < 5; i++) {
//                fluxSink.next(Util.faker().country().name());
//            }
//            fluxSink.complete();
//        }).subscribe(Util.subscriber());

        Flux.create(fluxSink -> {
           String country;
           do {
               country = Util.faker().country().name();
               fluxSink.next(country);
           } while (!country.equals("Canada"));
           fluxSink.complete();
        }).subscribe(Util.subscriber());
    }
}
