package edu.hw9.Task2;

import java.io.File;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileFinder extends RecursiveTask<List<File>> {

    private final File directory;
    private final Predicate<File> predicate;

    public FileFinder(File directory, Predicate<File> predicate) {
        this.directory = directory;
        this.predicate = predicate;
    }

    public List<File> findFile() {
        try (ForkJoinPool forkJoinPool = new ForkJoinPool()) {
            return forkJoinPool.invoke(this);
        }
    }

    @Override
    protected List<File> compute() {
        File[] files = directory.listFiles();

        if (files != null) {
            var matchingFiles = Stream.of(files)
                .filter(predicate)
                .collect(Collectors.toList());

            List<FileFinder> subtasks = createSubtasks(files);

            invokeAll(subtasks);

            subtasks.stream()
                .map(RecursiveTask::join)
                .forEach(matchingFiles::addAll);

            return matchingFiles;

        }
        return List.of();
    }

    private List<FileFinder> createSubtasks(File[] files) {
        return files != null ? Stream.of(files)
            .filter(File::isDirectory)
            .map(subDir -> new FileFinder(subDir, predicate))
            .collect(Collectors.toList()) : List.of();
    }

}
