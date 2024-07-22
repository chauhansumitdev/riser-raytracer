package com.raytracer.riser;

import java.util.List;

public class Scene {

    List<Sphere> objects;
    Vector camera;
    int rows;
    int cols;

    public Scene(List<Sphere> objects, Vector camera, int rows, int cols) {
        this.objects = objects;
        this.camera = camera;
        this.rows = rows;
        this.cols = cols;
    }
}
