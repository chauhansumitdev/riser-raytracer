package com.raytracer.riser;

public class Ray {

    Vector origin;
    Vector direction;

    public Ray(Vector origin, Vector direction) {
        this.origin = origin;
        this.direction = direction.normalize();
    }
}
