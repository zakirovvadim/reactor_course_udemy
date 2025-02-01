package ru.vadim.java.reactive.cource.tutorial.sec4.assingment;

import reactor.core.publisher.Flux;

import java.io.IOException;
import java.nio.file.Path;

public interface FileReaderService {

    Flux<String> read(Path path) throws IOException;
}
