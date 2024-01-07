package edu.project4;

import edu.project4.BaseObjects.FractalImage;
import edu.project4.BaseObjects.Pixel;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;

@Log4j2 final class ImageUtils {

    private static final int HEX = 16;
    private static final int OCT = 8;

    private ImageUtils() {

    }

    public static void save(FractalImage fractalImage, String filename, ImageFormat imageFormat) {
        BufferedImage bufferedImage = getBufferedImage(fractalImage);

        String format = switch (imageFormat) {
            case JPEG -> "jpeg";
            case BMP -> "bmp";
            case PNG -> "png";
        };

        try {
            ImageIO.write(bufferedImage, format, new File(filename + "." + format));
            log.info("Изображение успешно сохранено в " + filename);
        } catch (IOException e) {
            log.error("Ошибка при сохранении изображения: " + e.getMessage());
        }
    }

    @NotNull
    private static BufferedImage getBufferedImage(FractalImage fractalImage) {
        int width = fractalImage.width();
        int height = fractalImage.height();
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Pixel pixel = fractalImage.pixel(x, y);
                if (pixel != null) {
                    int rgb = (pixel.r() << HEX) | (pixel.g() << OCT) | pixel.b();
                    bufferedImage.setRGB(x, y, rgb);
                }
            }
        }
        return bufferedImage;
    }
}
