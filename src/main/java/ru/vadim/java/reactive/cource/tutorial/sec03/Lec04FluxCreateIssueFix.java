package ru.vadim.java.reactive.cource.tutorial.sec03;

import reactor.core.publisher.Flux;
import ru.vadim.java.reactive.cource.tutorial.courceUtil.Util;

public class Lec04FluxCreateIssueFix {

    public static void main(String[] args) {

        // только один инстанс - fl
        Flux.create(fl -> {
            String country;
            do {
                country = Util.faker().country().name();
                System.out.println("emitting : " + country);
                fl.next(country);
            } while (!country.equals("Russian Federation") && !fl.isCancelled()); //!fl.isCancelled() проверяем на отмену,
            // которая идет после take(3) без него, продьюсер будет пушить дальше. Чтобы не делать цикл можно использовать Flux.generate в след примере.
            fl.complete();
        }).take(5).subscribe(Util.onNext(), Util.onError(), Util.onComplete());

    }

}
