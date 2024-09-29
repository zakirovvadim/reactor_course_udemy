package ru.vadim.javareactivecourcetutorial.sec4;

import reactor.core.publisher.Flux;
import ru.vadim.javareactivecourcetutorial.courceUtil.Util;
import ru.vadim.javareactivecourcetutorial.sec4.helper.NameGenerator;

/*
FluxSink мы можем вынести и использовать отдельно, как в классе NameGenerator, и прописывать логику там, в методе генерейт
 */
public class NewLec02FluxCreateRefactor {

    public static void main(String[] args) {
        NameGenerator nameGenerator = new NameGenerator();
        Flux<String> stringFlux = Flux.create(nameGenerator);
        stringFlux.subscribe(Util.subscriber());

        for (int i = 0; i < 10; i++) {
            nameGenerator.generate();
        }
    }
}
