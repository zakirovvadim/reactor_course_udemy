package ru.vadim.java.reactive.cource.tutorial.sec12;

import ru.vadim.java.reactive.cource.tutorial.courceUtil.Util;
import ru.vadim.java.reactive.cource.tutorial.sec12.assingment.SlackMember;
import ru.vadim.java.reactive.cource.tutorial.sec12.assingment.SlackRoom;

public class Lec08SlackAssingment {

    public static void main(String[] args) {
        SlackRoom room = new SlackRoom("reactor");
        SlackMember vadim = new SlackMember("vadim");
        SlackMember ilsur = new SlackMember("ilsur");
        SlackMember albert = new SlackMember("albert");

        room.addMember(vadim);
        room.addMember(ilsur);

        vadim.says("hello all...");
        Util.sleepSeconds(4);

        ilsur.says("hello vadya...");
        vadim.says("I simply wanted to say hu...");
        Util.sleepSeconds(4);

        room.addMember(albert);
        albert.says("Hey guys.. glad to be here...");
    }
}
