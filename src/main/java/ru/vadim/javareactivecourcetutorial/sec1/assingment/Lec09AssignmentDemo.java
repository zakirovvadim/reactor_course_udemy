package ru.vadim.javareactivecourcetutorial.sec1.assingment;

import static ru.vadim.javareactivecourcetutorial.courceUtil.Util.*;

public class Lec09AssignmentDemo {
    public static void main(String[] args) {
        FileService.read("file03.txt")
                .subscribe(onNext(), onError(), onComplete());

//        FileService.write("file03.txt", "This is file 3")
//                .subscribe(onNext(), onError(), onComplete());

        FileService.delete("file03.txt")
                .subscribe(onNext(), onError(), onComplete());
    }
}
