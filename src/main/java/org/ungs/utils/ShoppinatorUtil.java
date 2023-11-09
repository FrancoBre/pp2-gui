package org.ungs.utils;

import java.awt.Desktop;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import javax.imageio.ImageIO;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class ShoppinatorUtil {

    public static final String PLUGINS_PATH = "plugins/";

    public static void openWebBrowser(String url) {
        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
            try {
                Desktop.getDesktop().browse(new URI(url));
            } catch (IOException | URISyntaxException e) {
                log.error("Error opening web browser: {}", e.getMessage());
            }
        } else {
            log.warn("Opening web browser is not supported on this platform.");
        }
    }

    @SneakyThrows
    public static BufferedImage fetchImageFromUrl(String imageUrl) {
        BufferedImage image;
        try {
            image = fetchImage(imageUrl);
            return resizeImage(image);
        } catch (Exception e) {
            image = ImageIO.read(new File("src/main/resources/img/default-image.png"));
        }

        return resizeImage(image);
    }

    private static BufferedImage fetchImage(String imageUrl) throws IOException {
        URL url = new URL(imageUrl);
        return ImageIO.read(url);
    }

    public static BufferedImage resizeImage(BufferedImage originalImage) {
        BufferedImage resizedImage = new BufferedImage(100, 100, originalImage.getType());
        Graphics2D graphics2D = resizedImage.createGraphics();
        graphics2D.drawImage(originalImage, 0, 0, 100, 100, null);
        graphics2D.dispose();
        return resizedImage;
    }

}
