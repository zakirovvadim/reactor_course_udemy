package ru.vadim.javareactivecourcetutorial.sec1.assingment.newAssingment;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

@Slf4j
public class FileServiceImpl implements FileService {

    private static final Path PATH = Path.of("src/main/resources/");


    @Override
    public Mono<String> read(String fileName) {
        return Mono.fromCallable(() -> Files.readString(PATH.resolve(fileName)));
    }

    @Override
    public Mono<Void> write(String fileName, String content) {
        return Mono.fromRunnable(() -> doWrite(fileName, content));

    }

    @Override
    public Mono<Void> delete(String fileName) {
        return Mono.fromRunnable(() -> doDeleting(fileName));
    }

    private String doRead(String name) throws IOException {
        try (FileReader reader = new FileReader(name)) {
            StringBuilder content = new StringBuilder();
            int nextChar;
            while ((nextChar = reader.read()) != -1) {
                content.append((char) nextChar);
            }
            String data = String.valueOf(content);
            return data;
        }
    }

    private void doWrite(String filName, String content) {
        /*
        Mine implementation:
        try (FileWriter fileWriter = new FileWriter(filName)) {
            fileWriter.write(content);
        }
        return Mono.empty();
         */
        try {
            Files.writeString(PATH.resolve(filName), content);
            log.info("created: {}", filName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void doDeleting(String fileName) {
       /*
       My implementation:
        try {
            Files.createFile(Paths.get("src/test/resources/fileToDelete_jdk7.txt"));
            Path fileToDeletePath = Paths.get("src/test/resources/fileToDelete_jdk7.txt");
            Files.delete(fileToDeletePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return Mono.empty();
        */
        try {
            Files.delete(PATH.resolve(fileName));
            log.info("Deleted: {}", fileName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
