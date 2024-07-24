package com.raytracer.riser;

import com.raytracer.ppm_to_jpg_converter.PPMToJPG;

public class Main {
    public static void main(String[] args) {
        Camera camera = new Camera(
                new Vector(0, 0, 0),
                new Vector(-2, -1, -1),
                new Vector(4, 0, 0),
                new Vector(0, 2, 0)
        );

        Sphere[] spheres = new Sphere[] {
                new Sphere(new Vector(0, 0, -1), 0.5),
                new Sphere(new Vector(0, -100.5, -1), 100),
                new Sphere(new Vector(1, 0, -1), 0.5),
                new Sphere(new Vector(-1, 0, -1), 0.5)
        };

        Scene scene = new Scene(spheres);
        Renderer renderer = new Renderer(camera, scene, 720, 1440, 100);

        Image image = new Image(720, 1440);
        renderer.render(image);

        image.createFile("in.ppm");

        String[] fileNames = new String[]{"in.ppm", "out.jpg"};
        PPMToJPG ppmToJPG = new PPMToJPG();
        ppmToJPG.convert(fileNames);
    }
}
