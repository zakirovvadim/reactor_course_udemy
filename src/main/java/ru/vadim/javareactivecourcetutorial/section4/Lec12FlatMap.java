package ru.vadim.javareactivecourcetutorial.section4;

import lombok.Data;
import ru.vadim.javareactivecourcetutorial.courceUtil.Util;

public class Lec12FlatMap {
}

@Data
class User {
    private int userId;
    private String name;

    public User(int userId) {
        this.userId = userId;
        this.name = Util.faker().name().fullName();
    }
}

@Data
class PurchaseOrder {

    private String item;
    private String price;
    private int userId;

    public PurchaseOrder(int userId) {
        this.item = item;
        this.item = Util.faker().commerce().productName();
        this.price = Util.faker().commerce().price();
    }
}
