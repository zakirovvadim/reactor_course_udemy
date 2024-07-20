package ru.vadim.javareactivecourcetutorial.section1.assingment.newAssingment;

import ru.vadim.javareactivecourcetutorial.courceUtil.Util;

public class Main {
    public static void main(String[] args) {
        FileServiceImpl fileService = new FileServiceImpl();
        fileService.write( "fileServiceTestFile.txt", "Vadim hello");
//        fileService.read("test.txt")
//                    .subscribe(Util.subscriber());
//        for (int i = 0; i < 1000; i++) {
//            fileService.read("src/main/java/ru/vadim/javareactivecourcetutorial/section1/assingment/newAssingment/test.txt")
//                    .subscribe(Util.subscriber());
//        }
    }
}
