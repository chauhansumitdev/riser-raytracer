package com.raytracer.ppm_to_jpg_converter;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;
import javax.imageio.ImageIO;

public class PPMToJPG {

    public void convert(String[] fileNames) {
        if (fileNames.length != 2) {
            System.err.println("Provide file names in array of String[2]{ <input.ppm>(path),<output.jpg>}");
            System.exit(1);
        }

        String inputFileName = fileNames[0];
        String outputFileName = fileNames[1];

        try {
            BufferedImage image = readPPM(inputFileName);
            ImageIO.write(image, "jpg", new File(outputFileName));
            System.out.println("Success: " + outputFileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private BufferedImage readPPM(String fileName) throws IOException {
        FileInputStream fis = new FileInputStream(fileName);
        Scanner scanner = new Scanner(fis);

        String format = scanner.next();
        if (!format.equals("P3") && !format.equals("P6")) {
            throw new IOException("Unsupported PPM format: " + format);
        }

        int width = scanner.nextInt();
        int height = scanner.nextInt();
        int maxColor = scanner.nextInt();

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        if (format.equals("P3")) {
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    int r = scanner.nextInt() * 255 / maxColor;
                    int g = scanner.nextInt() * 255 / maxColor;
                    int b = scanner.nextInt() * 255 / maxColor;
                    int rgb = (r << 16) | (g << 8) | b;
                    image.setRGB(x, y, rgb);
                }
            }
        } else {
            fis.read();
            byte[] pixelData = new byte[3];
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    fis.read(pixelData);
                    int r = (pixelData[0] & 0xFF) * 255 / maxColor;
                    int g = (pixelData[1] & 0xFF) * 255 / maxColor;
                    int b = (pixelData[2] & 0xFF) * 255 / maxColor;
                    int rgb = (r << 16) | (g << 8) | b;
                    image.setRGB(x, y, rgb);
                }
            }
        }

        scanner.close();
        fis.close();
        return image;
    }
}
