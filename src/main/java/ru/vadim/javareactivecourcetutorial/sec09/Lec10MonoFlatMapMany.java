package ru.vadim.javareactivecourcetutorial.sec09;

import ru.vadim.javareactivecourcetutorial.courceUtil.Util;
import ru.vadim.javareactivecourcetutorial.sec09.applications.OrderService;
import ru.vadim.javareactivecourcetutorial.sec09.applications.UserService;

import java.time.Duration;

/*
Если подпродьюсеры выдаю не моно, а флакс, тогда flatMap не подойдет.
Для этого надо использовать flatMapMany - поскольку мы намеренно говорим, что внутренний издатель — это не один элемент, он будет
предоставлять несколько элементов, надо подписаться на них, извлечь все элементы и предоставить их консьюмеру.
 */
public class Lec10MonoFlatMapMany {
    public static void main(String[] args) throws InterruptedException {
        UserService.getuserId("nastya")
                .flatMapMany(OrderService::getUserOrder)
                .subscribe(Util.subscriber());

        Thread.sleep(Duration.ofSeconds(2));
    }
}
