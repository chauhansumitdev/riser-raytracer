package com.raytracer.riser;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int width = 320;
        int height = 200;
        Vector camera = new Vector(0, 0, -1);

        Material redMaterial = new Material(new Color(1.0, 0.0, 0.0));
        Material greenMaterial = new Material(new Color(0.0, 1.0, 0.0));
        Material blueMaterial = new Material(new Color(0.0, 0.0, 1.0));

        List<Sphere> spheres = new ArrayList<>();
        spheres.add(new Sphere(new Vector(0.0, 0.0, 1.0), 0.5, redMaterial)); // Sphere in front of the camera
        spheres.add(new Sphere(new Vector(1.0, 0.0, 2.0), 0.5, greenMaterial)); // Sphere to the right
        spheres.add(new Sphere(new Vector(-1.0, 0.0, 2.0), 0.5, blueMaterial)); // Sphere to the left

        Scene scene = new Scene(spheres, camera, height, width);

        Engine engine = new Engine();
        Image image = engine.render(scene);

        image.createFile("output.ppm");
    }
}
