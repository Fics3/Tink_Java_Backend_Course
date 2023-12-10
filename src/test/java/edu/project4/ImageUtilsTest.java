package edu.project4;

import edu.project4.BaseObjects.FractalImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

public class ImageUtilsTest {

    private static final String TEST_DIRECTORY_NAME = "src/test/resources/project4";

    static Arguments[] formats() {
        return new Arguments[] {
            Arguments.of(ImageFormat.BMP, "testBmp"),
            Arguments.of(ImageFormat.JPEG, "testJpeg"),
            Arguments.of(ImageFormat.PNG, "testPng")
        };
    }

    @BeforeAll
    static void setUp() {
        try {
            Files.createDirectories(Path.of(TEST_DIRECTORY_NAME));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @AfterAll
    static void cleanUp() {
        deleteTempFiles();
    }

    @Test
    @DisplayName("save - Image to file")
    void save_WriteImage_BufferedImageToFile() {
        // Arrange
        String filename = TEST_DIRECTORY_NAME + "/testPng";
        FractalImage fractalImage = FractalImage.create(10, 10);
        ImageFormat imageFormat = ImageFormat.PNG;

        // Act
        ImageUtils.save(fractalImage, filename, imageFormat);

        // Assert
        assertThat(new File(filename + "." + imageFormat.toString().toLowerCase()).exists()).isTrue();

    }

    @ParameterizedTest
    @DisplayName("save - Image in jpeg")
    @MethodSource("formats")
    void save_WriteImage_BufferedImageInPng(ImageFormat imageFormat, String file) {
        // Arrange
        String filename = TEST_DIRECTORY_NAME + "/" + file;
        FractalImage fractalImage = FractalImage.create(10, 10);
        String expected = file + "." + imageFormat.toString().toLowerCase();

        // Act
        ImageUtils.save(fractalImage, filename, imageFormat);

        // Assert
        assertThat(new File(filename + "." + imageFormat.toString().toLowerCase())).hasName(expected);

    }

    private static void deleteTempFiles() {
        File testDirectory = new File(TEST_DIRECTORY_NAME);
        if (testDirectory.exists()) {
            try (var pathStream = Files.walk(testDirectory.toPath())) {
                pathStream.sorted((path1, path2) -> -path1.compareTo(path2))
                    .forEach(path -> {
                        try {
                            Files.delete(path);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
