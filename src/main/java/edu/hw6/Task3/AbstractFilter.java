package edu.hw6.Task3;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Pattern;

public interface AbstractFilter extends DirectoryStream.Filter<Path> {

    boolean accept(Path path);

    default AbstractFilter and(AbstractFilter abstractFilter) {
        return entry -> this.accept(entry) && abstractFilter.accept(entry);
    }

    AbstractFilter REGULAR_FILE = Files::isRegularFile;
    AbstractFilter READABLE = Files::isReadable;

    static AbstractFilter largerThan(int size) {
        return entry -> {
            try {
                return Files.size(entry) > size;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };
    }

    static AbstractFilter globMatches(String glob) {
        return entry -> {
            String fileName = entry.getFileName().toString();
            String ext = fileName.substring(fileName.lastIndexOf("."));
            return glob.equals(ext);

        };
    }

    static AbstractFilter regexContains(String regex) {
        return entry -> {
            Path fileName = entry.getFileName();
            if (fileName != null) {
                return Pattern.compile(regex).matcher(fileName.toString()).find();
            }
            return false;
        };
    }


    static AbstractFilter magicNumber(Object... magicBytes) {
        return entry -> {
            if (Files.isRegularFile(entry)) {
                try {
                    byte[] fileBytes = Files.readAllBytes(entry);
                    if (fileBytes.length >= magicBytes.length) {
                        for (int i = 0; i < magicBytes.length; i++) {
                            if (magicBytes[i] instanceof Byte && fileBytes[i] != (byte) magicBytes[i]) {
                                return false;
                            } else if (magicBytes[i] instanceof Character && fileBytes[i] != (char) magicBytes[i]) {
                                return false;
                            }
                        }
                        return true;
                    }
                } catch (IOException e) {
                    throw new RuntimeException("No such file");
                }
            }
            return false;
        };
    }

}
