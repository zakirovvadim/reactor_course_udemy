package ru.vadim.javareactivecourcetutorial.section02;

import ru.vadim.javareactivecourcetutorial.courceUtil.NameGenerator;

import java.util.List;

import static ru.vadim.javareactivecourcetutorial.courceUtil.Util.onNext;

public class Lec07FluxVsList {

    public static void main(String[] args) {
//        List<String> names = NameGenerator.getNames(5);
//        System.out.println(names);

        NameGenerator.getFluxNames(5).subscribe(onNext());
    }
}
