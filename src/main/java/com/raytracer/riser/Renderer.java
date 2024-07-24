package com.raytracer.riser;

import java.util.Random;

public class Renderer {
    private Camera camera;
    private Scene scene;
    private int rows;
    private int cols;
    private int samplesPerPixel;

    public Renderer(Camera camera, Scene scene, int rows, int cols, int samplesPerPixel) {
        this.camera = camera;
        this.scene = scene;
        this.rows = rows;
        this.cols = cols;
        this.samplesPerPixel = samplesPerPixel;
    }

    public void render(Image image) {
        Random rand = new Random();
        System.out.println("Rendering Image ...");

        int progress_count = cols/10;
        int count = 0;
        for (int i = 0; i < cols; i++) {
            if (i != 0 && i % progress_count == 0) {
                count += 10;
                System.out.println(count + "% completed.");
            }
            for (int j = 0; j < rows; j++) {
                Color pixelColor = new Color(0, 0, 0);
                for (int s = 0; s < samplesPerPixel; s++) {
                    double u = (i + rand.nextDouble()) / cols;
                    double v = (j + rand.nextDouble()) / rows;

                    Ray ray = camera.getRay(u, v);
                    pixelColor = pixelColor.add(scene.get_ray_color(ray, 0));
                }
                pixelColor = pixelColor.divide_by_scalar_clr(samplesPerPixel);
                image.setPixel(rows - 1 - j, i, pixelColor);
            }
        }
    }
}
