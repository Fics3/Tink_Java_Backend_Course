package edu.hw6.Task1;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class DiskMapTest {

    DiskMap diskMap;
    static final File EMPTY_FILE = new File("src/test/resources/Task1/emptyFile");
    static final File TO_CLEAR_FILE = new File("src/test/resources/Task1/toClearFile");
    static final File ONE_ELEMENT_FILE = new File("src/test/resources/Task1/oneElementFile");
    static final File NOT_EXIST_FILE = new File("src/test/resources/Task1/notExistFile");

    @BeforeAll
    static void setUp() throws IOException {
        //Arrange empty file
        if (EMPTY_FILE.delete()) {
            EMPTY_FILE.createNewFile();
        }

        //Arrange not empty file to clear
        PrintWriter printWriter = new PrintWriter(TO_CLEAR_FILE);
        printWriter.write("test1:test1\ntest2:test2");
        printWriter.close();

        //Arrange file with one element to remove
        printWriter = new PrintWriter(ONE_ELEMENT_FILE);
        printWriter.write("test:test");
        printWriter.close();
    }

    @AfterEach
    void cleanup() {
        if (NOT_EXIST_FILE.exists()) {
            NOT_EXIST_FILE.delete();
        }
    }

    @Test
    @DisplayName("Test for read from file when create")
    void DiskMap_shouldReadFromFile() {
        //Arrange && Act
        diskMap = new DiskMap("src/test/resources/Task1/notEmptyFile");

        //Assert
        assertThat(diskMap).isNotEmpty();
    }

    @Test
    @DisplayName("Test for read from not exist file when create")
    void DiskMap_shouldBeEmptyIfFileDoesNotExist() {
        //Arrange && Act
        diskMap = new DiskMap(NOT_EXIST_FILE.getPath());

        //Assert
        assertThat(NOT_EXIST_FILE.exists()).isFalse();
        assertThat(diskMap).isEmpty();
    }

    @Test
    @DisplayName("Test for put save to file test")
    void put_shouldSaveToEmptyFile() {
        //Arrange
        diskMap = new DiskMap(EMPTY_FILE.getPath());

        //Act
        diskMap.put("testKey", "testValue");

        //Assert
        assertThat(diskMap.containsKey("testKey")).isTrue();
        assertThat(EMPTY_FILE).isNotEmpty();
    }

    @Test
    @DisplayName("Test for put to not exist file")
    void put_shouldCreateNewFileIfNotExists() {
        //Arrange
        diskMap = new DiskMap(NOT_EXIST_FILE.getPath());

        //Act
        diskMap.put("test", "test");

        //Assert
        assertThat(NOT_EXIST_FILE.exists()).isTrue();
        assertThat(diskMap).isNotEmpty();
    }

    @Test
    @DisplayName("Test for clear should clear file")
    void clear_shouldClearFileAndDiskMap() {
        //Arrange
        diskMap = new DiskMap(TO_CLEAR_FILE.getPath());

        //Act
        diskMap.clear();

        //Assert that file empty after clear
        assertThat(diskMap).isEmpty();
        assertThat(TO_CLEAR_FILE).isEmpty();
    }

    @Test
    @DisplayName("Test for remove element from file")
    void remove_shouldRemoveElementFromFile() {
        //Arrange
        diskMap = new DiskMap(ONE_ELEMENT_FILE.getPath());

        //Act
        diskMap.remove("test");

        //Assert
        assertThat(diskMap).isEmpty();
        assertThat(ONE_ELEMENT_FILE).isEmpty();
    }
}
