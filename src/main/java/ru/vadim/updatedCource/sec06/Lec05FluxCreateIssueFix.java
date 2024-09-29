package ru.vadim.updatedCource.sec06;

import reactor.core.publisher.Flux;
import ru.vadim.javareactivecourcetutorial.courceUtil.Util;
import ru.vadim.javareactivecourcetutorial.sec4.helper.NameGenerator;

public class Lec05FluxCreateIssueFix {
    public static void main(String[] args) {
        /*
        Так не будет работать с двумя и более подписчиками, потому что создается только один генератор/производитель имен и это не горячая подписка
        var generator = new NameGenerator();
        var flux = Flux.create(generator);
        flux.subscribe(Util.subscriber("sub1"));
        flux.subscribe(Util.subscriber("sub2"));

        for (int i = 0; i < 10; i++) {
            generator.generate();
        }
         */

        // Для работы нужно сделать публикацию горячей, и просто добавить .share()
        var generator = new NameGenerator();
        var flux = Flux.create(generator).share();
        flux.subscribe(Util.subscriber("sub1"));
        flux.subscribe(Util.subscriber("sub2"));

        for (int i = 0; i < 10; i++) {
            generator.generate();
        }
    }
}
