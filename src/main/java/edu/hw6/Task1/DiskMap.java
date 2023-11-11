package edu.hw6.Task1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DiskMap implements Map<String, String> {

    private final Map<String, String> diskMap;
    private final String filename;

    public DiskMap(String filename) {
        this.filename = filename;
        diskMap = new HashMap<>();
        loadFromFile();
    }

    private void loadFromFile() {
        File file = new File(filename);
        if (file.exists()) {
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    String[] parsedLine = line.split(":");
                    if (parsedLine.length == 2) {
                        diskMap.put(parsedLine[0], parsedLine[1]);
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException("ERROR: File doesn't exist");
            }
        }
    }

    private void saveToFile() {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filename))) {
            for (var entry : diskMap.entrySet()) {
                bufferedWriter.write(entry.getKey() + ":" + entry.getValue());
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("ERROR");
        }
    }

    @Override
    public int size() {
        return diskMap.size();
    }

    @Override
    public boolean isEmpty() {
        return diskMap.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return diskMap.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return diskMap.containsValue(value);
    }

    @Override
    public String get(Object key) {
        return diskMap.get(key);
    }

    @Nullable
    @Override
    public String put(String key, String value) {
        String previousValue = diskMap.put(key, value);
        saveToFile();
        return previousValue;
    }

    @Override
    public String remove(Object key) {
        String previousValue = diskMap.remove(key);
        saveToFile();
        return previousValue;
    }

    @Override
    public void putAll(@NotNull Map<? extends String, ? extends String> map) {
        diskMap.putAll(map);
        saveToFile();
    }

    @Override
    public void clear() {
        diskMap.clear();
        saveToFile();
    }

    @NotNull
    @Override
    public Set<String> keySet() {
        return diskMap.keySet();
    }

    @NotNull
    @Override
    public Collection<String> values() {
        return diskMap.values();
    }

    @NotNull
    @Override
    public Set<Entry<String, String>> entrySet() {
        return diskMap.entrySet();
    }
}
