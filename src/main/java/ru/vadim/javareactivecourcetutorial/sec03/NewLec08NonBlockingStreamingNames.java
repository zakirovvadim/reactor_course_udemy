package ru.vadim.javareactivecourcetutorial.sec03;

import ru.vadim.javareactivecourcetutorial.courceUtil.Util;
import ru.vadim.javareactivecourcetutorial.sec02.client.ExternalServiceClient;

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
