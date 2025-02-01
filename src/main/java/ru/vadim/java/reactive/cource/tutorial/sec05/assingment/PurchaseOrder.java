package ru.vadim.java.reactive.cource.tutorial.sec05.assingment;

import lombok.Data;
import ru.vadim.java.reactive.cource.tutorial.courceUtil.Util;

@Data
public class PurchaseOrder {
    private String item;
    private double price;
    private String category;
    private int quantity;

    public PurchaseOrder() {
        this.item = Util.faker().commerce().productName();
        this.price = Double.parseDouble(Util.faker().commerce().price().replace(",", "."));
        this.category = Util.faker().commerce().department();
        this.quantity = Util.faker().random().nextInt(1, 10);
    }
}
