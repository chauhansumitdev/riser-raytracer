package com.raytracer.riser;

public class Sphere {

    Material material;
    Vector center;
    double radius;

    public Sphere(Vector center, double radius, Material material) {
        this.material = material;
        this.center = center;
        this.radius = radius;
    }

    public double isIntersected(Ray ray) {
        Vector l = ray.origin.subtract(center);

        double b = 2 * ray.direction.getDotProduct(l);
        double c = l.getDotProduct(l) - radius * radius;
        double discriminant = b * b - 4 * c;

        if (discriminant >= 0) {
            double dist = (-b - Math.sqrt(discriminant)) / 2;  // only considering the closer intersection

            if (dist > 0) {
                return dist;
            }
        }

        return -1.0;
    }
}
