package ru.vadim.updatedCource.sec06.assignment;

import ru.vadim.javareactivecourcetutorial.courceUtil.Util;
import ru.vadim.javareactivecourcetutorial.section02.client.ExternalServiceClient;

public class Lec06Assingment {
    public static void main(String[] args) {
        OrdersService ordersService = new OrdersService();
        InvetoryService invetoryService = new InvetoryService();
        RevenueService revenueService = new RevenueService();


        ordersService.getOrders().subscribe(invetoryService::consume);
        ordersService.getOrders().subscribe(revenueService::consume);

        invetoryService.stream()
                .subscribe(Util.subscriber("invetoryService"));

        revenueService.stream()
                .subscribe(Util.subscriber("revenueService"));

        Util.sleepSeconds(30);
    }
}
