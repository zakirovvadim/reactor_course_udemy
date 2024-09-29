package ru.vadim.javareactivecourcetutorial.sec02;

import ru.vadim.javareactivecourcetutorial.courceUtil.NameGenerator;
import ru.vadim.updatedCource.Lection01.subscriber.SubscriberImpl;

public class Lec07FluxVsList {

    public static void main(String[] args) {
//        List<String> names = NameGenerator.getNames(5);
//        System.out.println(names);

        SubscriberImpl subscriber = new SubscriberImpl();
        NameGenerator.getFluxNames(10) // не смотря на то, что указали 10
                .subscribe(subscriber);
        subscriber.getSubscription().request(3); // можем регулировать обеъктом подписки то, сколько анм надо
    }
}
