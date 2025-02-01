package ru.vadim.java.reactive.cource.tutorial.sec03;

import ru.vadim.java.reactive.cource.tutorial.courceUtil.Util;
import ru.vadim.java.reactive.cource.tutorial.sec02.client.ExternalServiceClient;

public class NewLec08NonBlockingStreamingNames {

    public static void main(String[] args) {
        ExternalServiceClient externalServiceClient = new ExternalServiceClient();
        externalServiceClient.getNames()
                .subscribe(Util.subscriber("sub1"));

        externalServiceClient.getNames()
                .subscribe(Util.subscriber("sub2"));

        Util.sleepSeconds(6);
    }
}
