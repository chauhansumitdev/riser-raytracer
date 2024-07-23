package com.raytracer.riser;

public class Sphere {

    Vector center;
    double radius;

    public Sphere(Vector center, double radius) {
        this.center = center;
        this.radius = radius;
    }

    public double is_intersected(Ray ray) {
        Vector l = ray.origin.subtract(center);

        double b = 2 * ray.direction.get_dot_product(l);
        double c = l.get_dot_product(l) - radius * radius;
        double discriminant = b * b - 4 * c;

        if (discriminant >= 0) {
            double dist = (-b - Math.sqrt(discriminant)) / 2;

            if (dist > 0) {
                return dist;
            }
        }

        return -1.0;
    }
}
