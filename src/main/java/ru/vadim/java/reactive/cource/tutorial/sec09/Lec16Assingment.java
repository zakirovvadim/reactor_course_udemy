package ru.vadim.java.reactive.cource.tutorial.sec09;

import reactor.core.publisher.Mono;
import ru.vadim.java.reactive.cource.tutorial.courceUtil.Util;
import ru.vadim.java.reactive.cource.tutorial.sec09.applications.*;
import ru.vadim.java.reactive.cource.tutorial.sec09.applications.*;

import java.util.List;

public class Lec16Assingment {
    record UserInformation(Integer userId, String username, Integer balance, List<Order> orders) {
    }

    public static void main(String[] args) throws InterruptedException {
        UserService.getAllUsers()
                .flatMap(Lec16Assingment::getUserInformation)
                .subscribe(Util.subscriber());

        Thread.sleep(5000);
    }

    private static Mono<UserInformation> getUserInformation(User user) {
        return Mono.zip(
                        PaymentService.getUserBalance(user.id()),
                        OrderService.getUserOrder(user.id()).collectList()
                )
                .map(el -> new UserInformation(user.id(), user.name(), el.getT1(), el.getT2()));
    }
}
