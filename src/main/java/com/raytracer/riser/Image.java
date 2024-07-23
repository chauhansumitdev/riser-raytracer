package com.raytracer.riser;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Image {

    int cols;
    int rows;

    int[][][] image;

    public Image(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        image = new int[rows][cols][3];
    }

    public void setPixel(int x, int y, Color color) {
        double xCurr = color.x;
        double yCurr = color.y;
        double zCurr = color.z;

        int red = (int) Math.round(Math.max(Math.min(xCurr * 256, 255), 0));
        int green = (int) Math.round(Math.max(Math.min(yCurr * 256, 255), 0));
        int blue = (int) Math.round(Math.max(Math.min(zCurr * 256, 255), 0));

        image[x][y][0] = red;
        image[x][y][1] = green;
        image[x][y][2] = blue;
    }

    public void createFile(String fileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {

            bufferedWriter.write("P3\n");
            bufferedWriter.write(cols + " " + rows + "\n");
            bufferedWriter.write("255\n");

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    for (int k = 0; k < 3; k++) {
                        bufferedWriter.write(image[i][j][k] + " ");
                    }
                }
                bufferedWriter.write("\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
