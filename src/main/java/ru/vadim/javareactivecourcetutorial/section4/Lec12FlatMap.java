package ru.vadim.javareactivecourcetutorial.section4;

import lombok.Data;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import ru.vadim.javareactivecourcetutorial.courceUtil.Util;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ru.vadim.javareactivecourcetutorial.courceUtil.Util.onNext;

/*
ФлатМап позволяет получить поток другого типа, например если мы сначала оплучаем юзеров, а потом нужно оплучить заказы
кажждого юзера, для этого мы обращаемся к методу окторый уже выдает Флакс заказов и принимает айди юзера
Без ФлатМап,а с обычным map - в мтеод получения заказа передавался бы не объект типа айди, а флакс (или моно).
 */
public class Lec12FlatMap {

    public static void main(String[] args) throws InterruptedException {
        Flux.range(1, 10)
                .filter(i -> i > 5)
                .take(2)
                .next()
                .subscribe(System.out::println);
//        UserService.getUsers()
//                .flatMap(user -> OrderService.getOrders(user.getUserId()))// if map - user.getUserId() == flux, а нет int
//                .subscribe(onNext(), Util.onError(), Util.onComplete());
//
//        Thread.sleep(60000);
    }

}

@Data
class User {
    private int userId;
    private String name;

    public User(int userId) {
        this.userId = userId;
        this.name = Util.faker().name().fullName();
    }
}

@Data
class PurchaseOrder {

    private String item;
    private String price;
    private int userId;

    public PurchaseOrder(int userId) {
        this.userId = userId;
        this.item = Util.faker().commerce().productName();
        this.price = Util.faker().commerce().price();
    }
}

class OrderService {
    private static Map<Integer, List<PurchaseOrder>> db = Map.of(
            1, List.of(new PurchaseOrder(1), new PurchaseOrder(1), new PurchaseOrder(1)),
            2, List.of(new PurchaseOrder(2), new PurchaseOrder(2)));

    public static Flux<PurchaseOrder> getOrders(int userId) {
        return Flux.create((FluxSink<PurchaseOrder> purchaseOrderFluxSink) -> { // без (FluxSink<PurchaseOrder> - на возврат метода нужно было бы ставить Object
            db.get(userId).forEach(purchaseOrderFluxSink::next);
            purchaseOrderFluxSink.complete();
        }).delayElements(Duration.ofSeconds(1)); // задержим элементы чтобы показать. что выдача идет не по порядку
    }
}

class UserService {
    public static Flux<User> getUsers() {
        return Flux.range(1, 2)
                .map(User::new);
    }
}