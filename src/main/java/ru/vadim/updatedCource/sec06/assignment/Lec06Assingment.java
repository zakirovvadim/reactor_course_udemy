package ru.vadim.updatedCource.sec06.assignment;

import ru.vadim.java.reactive.cource.tutorial.courceUtil.Util;

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
