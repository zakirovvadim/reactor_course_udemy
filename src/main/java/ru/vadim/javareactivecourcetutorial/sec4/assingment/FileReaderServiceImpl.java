package ru.vadim.javareactivecourcetutorial.sec4.assingment;

import reactor.core.publisher.Flux;
import reactor.core.publisher.SynchronousSink;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileReaderServiceImpl implements FileReaderService {

    @Override
    public Flux<String> read(Path path) throws IOException {
        return Flux.generate(
                () -> openReader(path), // открываем стейт объект
                (readder, sink) -> readFile(readder, sink), // передаем стейт объект с синком в мутод гдесчитываем линии файла
                (reader) -> closeFile(reader) // закрываем стейт
        );
    }


    private BufferedReader openReader(Path path) throws IOException {
        return Files.newBufferedReader(path);
    }

    private BufferedReader readFile(BufferedReader reader, SynchronousSink<String> sink) {
        try {
            String line = reader.readLine();
            if (line == null) {
                sink.complete();
            } else {
                sink.next(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return reader;
    }

    private void closeFile(BufferedReader reader) {
        try {
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
