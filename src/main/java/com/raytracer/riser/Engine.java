package com.raytracer.riser;

public class Engine {

    public NearestSphere findNearest(Ray ray, Scene scene) {
        double distMin = -1.0;
        Sphere objHit = null;

        for (Sphere sphere : scene.objects) {
            double intersectedDistance = sphere.isIntersected(ray);

            if (intersectedDistance != -1.0 && (objHit == null || intersectedDistance < distMin)) {
                distMin = intersectedDistance;
                objHit = sphere;
            }
        }

        return new NearestSphere(distMin, objHit);
    }

    public Color colorAtIntersection(Sphere sphere, Vector position) {
        return sphere.material.getColor();
    }

    public Color rayTrace(Ray ray, Scene scene) {
        Color color = new Color(0, 0, 0);

        NearestSphere nearestSphere = findNearest(ray, scene);

        if (nearestSphere.objHit == null) {
            return color;
        }

        Vector hitPos = ray.origin.add(ray.direction.multiplyByScalar(nearestSphere.distMin));
        color = color.add(colorAtIntersection(nearestSphere.objHit, hitPos));
        return color;
    }

    public Image render(Scene scene) {
        int width = scene.cols;
        int height = scene.rows;
        float aspectRatio = (float) width / height;
        float x0 = -1.0f;
        float x1 = 1.0f;
        float xStep = (x1 - x0) / (width - 1);
        float y0 = -1.0f / aspectRatio;
        float y1 = 1.0f / aspectRatio;
        float yStep = (y1 - y0) / (height - 1);

        Vector camera = scene.camera;
        Image pixels = new Image(width, height);

        for (int j = 0; j < height; j++) {
            float y = y0 + j * yStep;
            for (int i = 0; i < width; i++) {
                float x = x0 + i * xStep;
                Ray ray = new Ray(camera, new Point(x, y, 0).subtract(camera));
                pixels.setPixel(i, j, rayTrace(ray, scene));
            }
        }
        return pixels;
    }
}

class NearestSphere {
    double distMin;
    Sphere objHit;

    public NearestSphere(double distMin, Sphere objHit) {
        this.distMin = distMin;
        this.objHit = objHit;
    }
}
