package ru.vadim.java.reactive.cource.tutorial.sec1.assingment;

import ru.vadim.java.reactive.cource.tutorial.courceUtil.Util;

public class Lec09AssignmentDemo {
    public static void main(String[] args) {
        FileService.read("file03.txt")
                .subscribe(Util.onNext(), Util.onError(), Util.onComplete());

//        FileService.write("file03.txt", "This is file 3")
//                .subscribe(onNext(), onError(), onComplete());

        FileService.delete("file03.txt")
                .subscribe(Util.onNext(), Util.onError(), Util.onComplete());
    }
}
