package com.example.bookstore.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Objects;

@Service
public class ImageService {
    public byte[] resizeToWidth(MultipartFile imageFile, int desiredWidth) throws IOException {
        BufferedImage originalImage = ImageIO.read(imageFile.getInputStream());
        int currentWidth = originalImage.getWidth();
        int currentHeight = originalImage.getHeight();

        double scale = (double) desiredWidth / currentWidth;


        int scaledWidth = (int) (currentWidth * scale);
        int scaledHeight = (int) (currentHeight * scale);

        if (scale > 1) {
            BufferedImage scaledImage = resizeImage(originalImage, scaledWidth, scaledHeight);
            originalImage.flush();
            originalImage = scaledImage;
        }

        int x = (scaledWidth - desiredWidth) / 2;
        BufferedImage croppedImage = cropImage(originalImage, x, scaledHeight, desiredWidth, scaledHeight);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(croppedImage, getImageFormat(Objects.requireNonNull(imageFile.getOriginalFilename())), outputStream);
        return outputStream.toByteArray();
    }

    public byte[] resizeToHeight(MultipartFile imageFile, int desiredHeight) throws IOException {
        BufferedImage originalImage = ImageIO.read(imageFile.getInputStream());
        int currentWidth = originalImage.getWidth();
        int currentHeight = originalImage.getHeight();

        double scale = (double) desiredHeight / currentHeight;


        int scaledWidth = (int) (currentWidth * scale);
        int scaledHeight = (int) (currentHeight * scale);

        if (scale > 1) {
            BufferedImage scaledImage = resizeImage(originalImage, scaledWidth, scaledHeight);
            originalImage.flush();
            originalImage = scaledImage;
        }

        int y = (scaledHeight - desiredHeight) / 2;
        BufferedImage croppedImage = cropImage(originalImage, scaledWidth, y, scaledWidth, desiredHeight);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(croppedImage, getImageFormat(Objects.requireNonNull(imageFile.getOriginalFilename())), outputStream);
        return outputStream.toByteArray();
    }

    private BufferedImage resizeImage(BufferedImage image, int width, int height) {
        Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = resizedImage.createGraphics();
        graphics.drawImage(scaledImage, 0, 0, null);
        graphics.dispose();
        return resizedImage;
    }

    private BufferedImage cropImage(BufferedImage image, int x, int y, int width, int height) {
        return image.getSubimage(x, y, width, height);
    }

    private String getImageFormat(String filename) {
        String extension = filename.substring(filename.lastIndexOf('.') + 1);
        return switch (extension.toLowerCase()) {
            case "jpeg", "jpg" -> "jpeg";
            case "png" -> "png";
            case "gif" -> "gif";
            default -> throw new IllegalArgumentException("Unsupported image format: " + extension);
        };
    }
}
