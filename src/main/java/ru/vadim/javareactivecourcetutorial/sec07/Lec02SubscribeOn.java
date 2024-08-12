package ru.vadim.javareactivecourcetutorial.sec07;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;
import ru.vadim.javareactivecourcetutorial.courceUtil.Util;
/*
Schedulers.immediate()может использоваться как нулевой объект , когда API требует, Scheduler но вы не хотите менять потоки
Schedulers.single()предназначен для одноразовых задач, которые могут быть запущены на уникальномExecutorService
Schedulers.parallel()хорош для ресурсоемких, но кратковременных задач. Он может выполнять N такие задачи параллельно
(по умолчанию N == number of CPUs)
Schedulers.elastic()и Schedulers.boundedElastic()хороши для более длительных задач (например, блокировка задач ввода-вывода).
Первый elastic порождает потоки по требованию без ограничений, тогда как недавно представленный boundedElastic делает то же самое
с ограничением на количество создаваемых потоков.
 */
@Slf4j
public class Lec02SubscribeOn {
    public static void main(String[] args) throws InterruptedException {

        /*

         SubscribeOn позволяет нам запускать выполнение в другом потоке, используя класс Schedulers. Данный класс предоставляет
         пулы потокв разного типа immediately , single , elastic и parallel. Соответственно используя subscribeOn
         мы посылаем сигнал по восходящему потоку, что исполнение будет проводится в другом потоке, поэтому у нас оператор doFirst
         который находится ниже мтеода сабсскрайбОн будет выполнятся в потоке который запустил Flux, а все что выше subscribeOn
         выполняется в одном из потоков пула, тип которого мы добавляем в аргументы этого оператора.

        18:13:39.393 [Thread-0] INFO ru.vadim.javareactivecourcetutorial.sec07.Lec02SubscribeOn -- first2   -- Thread-0 поток из которого запустили флакс, через раннабл, если бы запустили без раннбл, то был бы просто поток main
        18:13:39.397 [boundedElastic-1] INFO ru.vadim.javareactivecourcetutorial.sec07.Lec02SubscribeOn -- first1 -- этот ферст уже в другом потоке
        18:13:39.400 [boundedElastic-1] INFO ru.vadim.javareactivecourcetutorial.sec07.Lec02SubscribeOn -- value : 0,
        null - Received 0
        18:13:39.403 [boundedElastic-1] INFO ru.vadim.javareactivecourcetutorial.sec07.Lec02SubscribeOn -- value : 1,
        null - Received 1
        18:13:39.403 [boundedElastic-1] INFO ru.vadim.javareactivecourcetutorial.sec07.Lec02SubscribeOn -- value : 2,
        null - Received 2
        null - Completed

         */

        Flux<Object> flux = Flux.create(fluxSink -> {
                    for (int i = 0; i < 3; i++) {
                        fluxSink.next(i);
                    }
                    fluxSink.complete();
                })
                .doOnNext(i -> log.info("value : {},", i))
                .doFirst(() -> log.info("first1"))
                .subscribeOn(Schedulers.boundedElastic())
                .doFirst(() -> log.info("first2"));

        Runnable runnable = () -> flux
                .subscribe(Util.subscriber());

        Runnable runnable2 = () -> flux
                .subscribe(Util.subscriber()); // если мы добавим второго потребителя, то запросятся два потока из пула для Thread-0 boundedElastic-1 и для Thread-1 boundedElastic-2
        /*
        18:23:39.871 [Thread-0] INFO ru.vadim.javareactivecourcetutorial.sec07.Lec02SubscribeOn -- first2
        18:23:39.871 [Thread-1] INFO ru.vadim.javareactivecourcetutorial.sec07.Lec02SubscribeOn -- first2
        18:23:39.875 [boundedElastic-2] INFO ru.vadim.javareactivecourcetutorial.sec07.Lec02SubscribeOn -- first1
        18:23:39.875 [boundedElastic-1] INFO ru.vadim.javareactivecourcetutorial.sec07.Lec02SubscribeOn -- first1
         */

        Thread.ofPlatform().start(runnable);
        Thread.ofPlatform().start(runnable2);

        Thread.sleep(5000);
    }
}
