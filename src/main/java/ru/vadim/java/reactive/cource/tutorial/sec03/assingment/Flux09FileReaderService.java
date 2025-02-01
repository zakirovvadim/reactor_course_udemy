package ru.vadim.java.reactive.cource.tutorial.sec03.assingment;

import ru.vadim.java.reactive.cource.tutorial.courceUtil.Util;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Flux09FileReaderService {

    public static void main(String[] args) {

        FileREaderService fileREaderService = new FileREaderService();

        Path path = Paths.get("src/main/resources/assingment.sec03/file01.txt");

        fileREaderService.read(path)
//                .take(10)
                .subscribe(Util.onNext());
    }
}
