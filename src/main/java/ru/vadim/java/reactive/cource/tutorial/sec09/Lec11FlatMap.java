package ru.vadim.java.reactive.cource.tutorial.sec09;

import ru.vadim.java.reactive.cource.tutorial.courceUtil.Util;
import ru.vadim.java.reactive.cource.tutorial.sec09.applications.OrderService;
import ru.vadim.java.reactive.cource.tutorial.sec09.applications.User;
import ru.vadim.java.reactive.cource.tutorial.sec09.applications.UserService;

/*
флэтмап позволяет объеденить продьсеров. Внутри он работает так, что на каждый продьюсе основного потока выоплняется по одному
потреблению из других продьюсеров, т.е. если несколько продьюсеров, не будет ожидать полного
потребления, а возьмет один элемент. Запрос на подписку происходит одновременно с дефолтным ограничением в 256 (нужной быть осторожным
так как при подключении могут устанавлиать хттп соединения на указанное количество конкаренси)
flatMap: Используется для преобразования каждого элемента исходного потока в поток и последующего объединения этих потоков.
То есть трансформирует элементы.
merge: Просто объединяет несколько потоков в один, не трансформируя элементы.
 */
public class Lec11FlatMap {
    public static void main(String[] args) {
        UserService.getAllUsers()
                .map(User::id)
                .flatMap(OrderService::getUserOrder, 100)
                .subscribe(Util.subscriber());

        Util.sleepSeconds(5);
    }

}
