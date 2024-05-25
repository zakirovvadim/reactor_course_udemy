package ru.vadim.javareactivecourcetutorial.section05;

import ru.vadim.javareactivecourcetutorial.courceUtil.Util;
import ru.vadim.javareactivecourcetutorial.section05.assingment.InventoryService;
import ru.vadim.javareactivecourcetutorial.section05.assingment.OrderService;
import ru.vadim.javareactivecourcetutorial.section05.assingment.RevenueService;

public class Lec06Assingment {

    public static void main(String[] args) throws InterruptedException {
        OrderService orderService = new OrderService();
        RevenueService revenueService = new RevenueService();
        InventoryService inventoryService = new InventoryService();

        orderService.orderStream().subscribe(revenueService.subscribeOrderStream());
        orderService.orderStream().subscribe(inventoryService.subscribeOrderStream());

        inventoryService.inventoryStream()
                .subscribe(Util.subscriber("inventory"));
        revenueService.revenueStream()
                .subscribe(Util.subscriber("revenue"));

        Thread.sleep(60000);
    }
}
