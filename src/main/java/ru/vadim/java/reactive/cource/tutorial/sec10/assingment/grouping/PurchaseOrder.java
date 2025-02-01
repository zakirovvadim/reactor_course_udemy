package ru.vadim.java.reactive.cource.tutorial.sec10.assingment.grouping;

import ru.vadim.updatedCource.common.Util;

public record PurchaseOrder(String item, String category, Integer price) {

    public static PurchaseOrder create() {
        var order = Util.faker().commerce();
        return new PurchaseOrder(order.productName(), order.department(), Util.faker().random().nextInt(10, 100));
    }
}
