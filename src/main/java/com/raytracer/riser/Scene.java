package com.raytracer.riser;

public class Scene {
    private Sphere[] spheres;

    public Scene(Sphere[] spheres) {
        this.spheres = spheres;
    }

    public Color get_ray_color(Ray ray, int depth) {
        Sphere hitSphere = null;
        double closestT = Double.MAX_VALUE;
        Vector hitPoint = null;
        Vector normal = null;

        for (Sphere sphere : spheres) {
            double t = sphere.is_intersected(ray);
            if (t > 0 && t < closestT) {
                closestT = t;
                hitSphere = sphere;
                hitPoint = ray.get_position_at_t(t);
                normal = hitPoint.subtract(sphere.center).normalize();
            }
        }

        if (hitSphere != null) {
            Ray scattered = new Ray(hitPoint, normal.add(Vector.random_in_unit_sphere()));
            Color attenuation = new Color(1, 1, 1); // Initial attenuation

            if (depth < 50 && hitSphere.diffuse_scatter(ray, hitPoint, normal, attenuation, scattered)) {
                Color scatteredColor = get_ray_color(scattered, depth + 1);
                return attenuation.multiply(scatteredColor);
            } else {
                return new Color(0, 0, 0);
            }
        } else {
            Vector normalizedDir = ray.direction.normalize();
            double t = 0.5 * (normalizedDir.y + 1.0);
            Vector a = new Vector(1, 1, 1);
            Vector b = new Vector(0.5, 0.7, 1);
            Vector blendedColor = a.multiply_by_scalar(1 - t).add(b.multiply_by_scalar(t));
            return new Color(blendedColor.x, blendedColor.y, blendedColor.z);
        }
    }
}
