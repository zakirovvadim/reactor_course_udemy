package ru.vadim.java.reactive.cource.tutorial.sec07;

import lombok.extern.slf4j.Slf4j;
import ru.vadim.java.reactive.cource.tutorial.sec07.client.ExternalServiceClient;
import ru.vadim.java.reactive.cource.tutorial.courceUtil.Util;

/*
Без .publishOn(Schedulers.boundedElastic()); в ExternalServiceClient данные из сокета будут получать последовательно
т.е. заправшиваем, потом метод process, для того чтобы обратились к сокетам одновременно, надо при обращении по урлу
добавлять .publishOn(Schedulers.boundedElastic())
 */
@Slf4j
public class Lec06EventLoopIssueFix {

    public static void main(String[] args) {
        ExternalServiceClient client = new ExternalServiceClient();

        for (int i = 1; i <= 5; i++) {
            client.getProductName(i)
                    .map(Lec06EventLoopIssueFix::process)
                    .subscribe(Util.subscriber());
        }

        Util.sleepSeconds(10);
    }

    private static String process(String input) {
        Util.sleepSeconds(1);
        return input + "-processed";
    }
}
