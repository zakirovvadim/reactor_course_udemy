package ru.vadim.java.reactive.cource.tutorial.sec4.assingment;

import ru.vadim.java.reactive.cource.tutorial.courceUtil.Util;

import java.io.IOException;
import java.nio.file.Path;

public class Assingment {
    public static void main(String[] args) throws IOException {
        FileReaderServiceImpl fileReaderService = new FileReaderServiceImpl();
        fileReaderService.read(Path.of("src/main/resources/fileServiceTestFile.txt"))
                .subscribe(Util.subscriber());
    }
}
