package edu.hw9.Task2;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DirectorySearcher extends RecursiveTask<List<File>> {

    private static final int MIN_FILES = 1000;
    private final File directory;
    private final ArrayList<File> filesResult = new ArrayList<>();

    public DirectorySearcher(File directory) {
        this.directory = directory;
    }

    public List<File> findDirectories() {
        try (ForkJoinPool forkJoinPool = new ForkJoinPool()) {
            return forkJoinPool.invoke(this);
        }
    }

    @Override
    protected List<File> compute() {
        File[] files = directory.listFiles();

        if (files != null && files.length >= MIN_FILES) {
            filesResult.add(directory);
        }

        List<DirectorySearcher> subtasks = createSubtasks(files);

        invokeAll(subtasks);

        subtasks.stream()
            .map(RecursiveTask::join)
            .forEach(filesResult::addAll);

        return filesResult;
    }

    private List<DirectorySearcher> createSubtasks(File[] files) {
        return files != null ? Stream.of(files)
            .filter(File::isDirectory)
            .map(DirectorySearcher::new)
            .collect(Collectors.toList()) : List.of();
    }

}
