package com.raytracer.riser;

public class Ray {

    Vector origin;
    Vector direction;

    public Ray(Vector origin, Vector direction) {
        this.origin = origin;
        this.direction = direction.normalize();
    }

    public Vector get_position_at_t(double t){
        return origin.add(direction.multiply_by_scalar(t));
    }
}
