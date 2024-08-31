package ru.vadim.javareactivecourcetutorial.sec09;


import ru.vadim.javareactivecourcetutorial.sec09.helper.mergeUsecase.Aviasales;
import ru.vadim.updatedCource.common.Util;

import java.time.Duration;

public class Lec06MergeUsecase {
    public static void main(String[] args) throws InterruptedException {
        Aviasales.getFlights()
                .subscribe(Util.subscriber());
        Thread.sleep(Duration.ofSeconds(5));
    }
}
