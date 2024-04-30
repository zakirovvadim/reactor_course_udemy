package ru.vadim.javareactivecourcetutorial.section03.assingment;

import java.nio.file.Path;
import java.nio.file.Paths;

import static ru.vadim.javareactivecourcetutorial.courceUtil.Util.onNext;

public class Flux09FileReaderService {

    public static void main(String[] args) {

        FileREaderService fileREaderService = new FileREaderService();

        Path path = Paths.get("src/main/resources/assingment.sec03/file01.txt");

        fileREaderService.read(path)
//                .take(10)
                .subscribe(onNext());
    }
}
