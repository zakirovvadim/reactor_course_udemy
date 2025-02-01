package ru.vadim.java.reactive.cource.tutorial.sec4;

import reactor.core.publisher.Flux;
import ru.vadim.java.reactive.cource.tutorial.courceUtil.Util;

//take() - отправляет сигнал кансел и вызывается срау doFinally(), но если ты не соблюдаешь спецификацию реактивного
// потока и все равно продолжаешь пушить как на примере, но после take() буду след отмененные onDiscard элементы
//Еще заметка, doFinally лучше делать последним оператором
public class Lec02_03_04DoCallback {
    public static void main(String[] args) {
        Flux.create(fluxSink -> {
            System.out.println("inside create");
            for (int i = 0; i < 5; i++) {
                fluxSink.next(i);
            }
//            fluxSink.error(new RuntimeException("oops"));
            fluxSink.complete();
            System.out.println("--complete");
        })
                .doOnComplete(() -> System.out.println("doOnComplete"))
                .doFirst(() ->System.out.println("doFirst 1")) // doFirst оператор выполняется первым, где бы в этом пайплайне он не находился, елси несколько, снизу вверх. Так как в конце подписка сабскрайб и выполнение вверх.
                .doOnNext(o -> System.out.println("doOnNext  " + o))
                .doOnSubscribe(s -> System.out.println("doOnSubscribe 1" + s)) // выоплняется сверху вниз
                .doOnRequest(l -> System.out.println("doOnRequst : " + l))
                .doOnError(err -> System.out.println("doOnError : " + err.getMessage()))
                .doOnTerminate(() -> System.out.println("doOnTerminate"))
                .doOnCancel(() -> System.out.println("doOnCancel"))
                .doOnDiscard(Object.class, o -> System.out.println("doOnDiscard : " + o))
                .take(2)
                .doFinally(signal -> System.out.println("doFinally : " + signal))
                .subscribe(Util.onNext(), Util.onError(), Util.onComplete());
    }
}
/*
doFirst 1
doOnSubscribe 1reactor.core.publisher.FluxPeekFuseable$PeekConditionalSubscriber@7a36aefa
doOnRequst : 9223372036854775807
inside create
doOnNext  0
Received : 0
doOnNext  1
Received : 1
doOnNext  2
Received : 2
doOnNext  3
Received : 3
doOnNext  4
Received : 4
doOnComplete
doOnTerminate
Complete
doFinally : onComplete
--complete

Process finished with exit code 0
 */