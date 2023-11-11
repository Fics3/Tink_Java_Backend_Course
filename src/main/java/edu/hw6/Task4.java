package edu.hw6;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;

final class Task4 {

    private Task4() {

    }

    public static void chainPrintInFile(String filePath) {

        try (OutputStream outputStream = Files.newOutputStream(Path.of(filePath))) {
            CheckedOutputStream checkedOutputStream = new CheckedOutputStream(outputStream, new CRC32());
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(checkedOutputStream);
            OutputStreamWriter outputStreamWriter =
                new OutputStreamWriter(bufferedOutputStream, StandardCharsets.UTF_8);
            PrintWriter printWriter = new PrintWriter(
                outputStreamWriter,
                true
            ); //Остальные ресурсы должны сами закрываться, но принтер райтер не хочет, поэтому добавил автофлюш
            printWriter.println("Programming is learned by writing programs. ― Brian Kernighan");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}

