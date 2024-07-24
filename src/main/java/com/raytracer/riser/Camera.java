package com.raytracer.riser;

public class Camera {
    private Vector origin;
    private Vector leftCorner;
    private Vector horizontal;
    private Vector vertical;

    public Camera(Vector origin, Vector leftCorner, Vector horizontal, Vector vertical) {
        this.origin = origin;
        this.leftCorner = leftCorner;
        this.horizontal = horizontal;
        this.vertical = vertical;
    }

    public Ray getRay(double u, double v) {
        Vector offset = horizontal.multiply_by_scalar(u).add(vertical.multiply_by_scalar(v));
        Vector direction = offset.add(leftCorner).subtract(origin);
        return new Ray(origin, direction);
    }
}
