package ru.vadim.javareactivecourcetutorial.sec09;

import ru.vadim.javareactivecourcetutorial.courceUtil.Util;
import ru.vadim.javareactivecourcetutorial.sec09.helper.NameGenerator;

import java.time.Duration;
/*
Кейс использования оператора startWith мб следующим. Например, первоначальное получение данных из кеша. Пример в классе
NameGenerate.
List<String> redis = new ArrayList<>();
    public Flux<String> generateName() {
        return Flux.generate(synchronousSink -> {
            log.info("Start generating name");
            Util.sleepSeconds(1);
            String name = Util.faker().name().firstName();
            redis.add(name);
            synchronousSink.next(name); при первом запросе генерируем, т.е. выполняем тяжелое действие, и помещаем в кеш (типа редис)
        })
                .startWith(redis) забираем сначала из кеша, для последующих потребителей
                .cast(String.class);
    }
 */
public class Lec02StartWithUseCase {
    public static void main(String[] args) {
        NameGenerator nameGenerator = new NameGenerator();
        nameGenerator.generateName()
                .take(3)
                .subscribe(Util.subscriber("vadim"));
        nameGenerator.generateName()
                .take(3)
                .subscribe(Util.subscriber("nastya"));
        nameGenerator.generateName()
                .take(4)
                .subscribe(Util.subscriber("albert"));

        Util.sleep(Duration.ofSeconds(10));
    }
}
