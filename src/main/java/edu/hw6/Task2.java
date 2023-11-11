package edu.hw6;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import lombok.extern.log4j.Log4j2;

@Log4j2 final class Task2 {

    private Task2() {

    }

    public static void cloneFile(Path path) {
        if (Files.exists(path)) {
            Path parentDir = path.getParent();
            String fileName = path.getFileName().toString();
            String baseName = fileName.replaceFirst("[.][^.]+$", "");
            String ext = fileName.substring(fileName.lastIndexOf("."));

            int index = 0;

            while (true) {
                String newName =
                    (index == 0) ? baseName + " - копия" + ext : baseName + " - копия(" + index + ")" + ext;
                Path newPath = parentDir.resolve(newName);
                if (Files.exists(newPath)) {
                    index++;
                } else {
                    try {
                        Files.copy(path, newPath);
                        break;
                    } catch (FileAlreadyExistsException e) {
                        throw new IllegalArgumentException("File already exists");
                    } catch (IOException e) {
                        throw new RuntimeException("Can't copy this file");
                    }
                }
            }
        } else {
            log.error("File doesn't exist");
        }

    }
}
