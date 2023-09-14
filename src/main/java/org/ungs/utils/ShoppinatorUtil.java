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
        } catch (IOException e) {
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

    /*public static List<Product> createMockProductList() {
        List<Product> productList = new ArrayList<>();

        ProductImage productImage1 = new ProductImage(
            "https://ps.w.org/tiny-compress-images/assets/icon-256x256.png?rev=1088385");
        ShopProduct shopProduct1 = new ShopProduct("https://www.youtube.com/watch?v=dQw4w9WgXcQ");
        ProductPresentation productPresentation1 = new ProductPresentation(BigDecimal.valueOf(49.99), shopProduct1,
            productImage1);
        productList.add(new Product("El mejor producto de la historia", productPresentation1));

        ProductImage productImage2 = new ProductImage("https://i.stack.imgur.com/GsDIl.jpg");
        ShopProduct shopProduct2 = new ShopProduct("https://www.youtube.com/watch?v=dQw4w9WgXcQ");
        ProductPresentation productPresentation2 = new ProductPresentation(BigDecimal.valueOf(79.99), shopProduct2,
            productImage2);
        productList.add(new Product("El segundo mejor producto", productPresentation2));

        return productList;
    }*/
}
