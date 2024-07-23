package com.raytracer.riser;
import  com.raytracer.ppm_to_jpg_converter.PPMToJPG;

import java.util.Random;

public class Main {
    public static void main(String[] args) {

        Vector left_corner = new Vector(-2, -1, -1);
        Vector horizontal = new Vector(4, 0, 0);
        Vector vertical = new Vector(0, 2, 0);
        Vector origin = new Vector(0, 0, 0);

        int samplesPerPixel = 100;

        Image image = new Image(720, 1440);

        Sphere sphere = new Sphere(new Vector(0, 0, -1), 0.5);
        Sphere ground = new Sphere(new Vector(0, -100.5, -1), 100);

        Random rand = new Random();

        System.out.println("Rendering: Estimated time 1min");

        for (int i = 0; i < 1440; i++) {
            for (int j = 0; j < 720; j++) {
                Color pixelColor = new Color(0, 0, 0);
                for (int s = 0; s < samplesPerPixel; s++) {
                    double u = (i + rand.nextDouble()) / 1440;
                    double v = (j + rand.nextDouble()) / 720;

                    Vector o = horizontal.multiply_by_scalar(u);
                    Vector n = vertical.multiply_by_scalar(v);
                    Vector e = o.add(n);
                    Vector f = e.add(left_corner);

                    Ray ray = new Ray(origin, f);

                    pixelColor = pixelColor.add(getRayColor(ray, sphere, ground));

                }
                pixelColor = pixelColor.divide_by_scalar_clr(samplesPerPixel);
                image.setPixel(719-j,i,pixelColor);
            }
        }

        image.createFile("in.ppm");

        String[] fileNames = new String[]{"in.ppm", "out.jpg"};
        PPMToJPG ppmToJPG = new PPMToJPG();
        ppmToJPG.convert(fileNames);
    }

    private static Color getRayColor(Ray ray, Sphere sphere, Sphere ground) {
        double t_sphere = sphere.is_intersected(ray);
        double t_ground = ground.is_intersected(ray);

        if (t_sphere > 0 && t_ground > 0) {
            if (t_sphere < t_ground) {
                Vector sphere_normal = ray.get_position_at_t(t_sphere).subtract(sphere.center).normalize();
                return new Color((sphere_normal.x + 1) * 0.5, (sphere_normal.y + 1) * 0.5, (sphere_normal.z + 1) * 0.5);
            } else {
                Vector ground_normal = ray.get_position_at_t(t_ground).subtract(ground.center).normalize();
                return new Color((ground_normal.x + 1) * 0.5, (ground_normal.y + 1) * 0.5, (ground_normal.z + 1) * 0.5);
            }
        } else if (t_sphere > 0) {
            Vector sphere_normal = ray.get_position_at_t(t_sphere).subtract(sphere.center).normalize();
            return new Color((sphere_normal.x + 1) * 0.5, (sphere_normal.y + 1) * 0.5, (sphere_normal.z + 1) * 0.5);
        } else if (t_ground > 0) {
            Vector ground_normal = ray.get_position_at_t(t_ground).subtract(ground.center).normalize();
            return new Color((ground_normal.x + 1) * 0.5, (ground_normal.y + 1) * 0.5, (ground_normal.z + 1) * 0.5);
        } else {
            Vector normalized_dir = ray.direction.normalize();
            double t = 0.5 * (normalized_dir.y + 1.0);
            Vector a = new Vector(1, 1, 1);
            Vector b = new Vector(0.5, 0.7, 1);
            Vector blended_color = a.multiply_by_scalar(1 - t).add(b.multiply_by_scalar(t));
            return new Color(blended_color.x, blended_color.y, blended_color.z);
        }
    }
}








//         Vector left_corner = new Vector(-2, -1, -1);
//
//            Vector horizontal = new Vector(4, 0, 0);
//            Vector vertical = new Vector(0, 2, 0);
//            Vector origin = new Vector(0, 0, 0);
//
//            Image image = new Image(100, 200);
//
//            Sphere sphere = new Sphere(new Vector(0, 0, -1), 0.5);
//            Sphere ground = new Sphere(new Vector(0, -100.5, -1), 100);
//
//            for (int i = 0; i < 200; i++) {
//                for (int j = 0; j < 100; j++) {
//                    double u = (double) i / 200;
//                    double v = (double) j / 100;
//
//                    Vector o = horizontal.multiply_by_scalar(u);
//                    Vector n = vertical.multiply_by_scalar(v);
//                    Vector e = o.add(n);
//                    Vector f = e.add(left_corner);
//
//                    Ray ray = new Ray(origin, f);
//
//                    double t_sphere = sphere.is_intersected(ray);
//                    double t_ground = ground.is_intersected(ray);
//
//                    if (t_sphere > 0 && t_ground > 0) {
//                        if (t_sphere < t_ground) {
//                            Vector sphere_normal = ray.get_position_at_t(t_sphere).subtract(sphere.center).normalize();
//                            Color sphere_color = new Color((sphere_normal.x + 1) * 0.5, (sphere_normal.y + 1) * 0.5, (sphere_normal.z + 1) * 0.5);
//                            image.setPixel( 99-j,  i, sphere_color);
//                        } else {
//                            Vector ground_normal = ray.get_position_at_t(t_ground).subtract(ground.center).normalize();
//                            Color ground_color = new Color((ground_normal.x + 1) * 0.5, (ground_normal.y + 1) * 0.5, (ground_normal.z + 1) * 0.5);
//                            image.setPixel(99-j,  i, ground_color);
//                        }
//                    } else if (t_sphere > 0) {
//                        Vector sphere_normal = ray.get_position_at_t(t_sphere).subtract(sphere.center).normalize();
//                        Color sphere_color = new Color((sphere_normal.x + 1) * 0.5, (sphere_normal.y + 1) * 0.5, (sphere_normal.z + 1) * 0.5);
//                        image.setPixel(99-j,  i, sphere_color);
//                    } else if (t_ground > 0) {
//                        Vector ground_normal = ray.get_position_at_t(t_ground).subtract(ground.center).normalize();
//                        Color ground_color = new Color((ground_normal.x + 1) * 0.5, (ground_normal.y + 1) * 0.5, (ground_normal.z + 1) * 0.5);
//                        image.setPixel(99-j,  i, ground_color);
//                    } else {
//                        Vector normalized_dir = ray.direction.normalize();
//                        double t = 0.5 * (normalized_dir.y + 1.0);
//                        Vector a = new Vector(1, 1, 1);
//                        Vector b = new Vector(0.5, 0.7, 1);
//                        Vector blended_color = a.multiply_by_scalar(1 - t).add(b.multiply_by_scalar(t));
//                        Color color = new Color(blended_color.x, blended_color.y, blended_color.z);
//                        image.setPixel(99-j,  i, color);
//                    }
//
//                }
//            }
//
//            image.createFile("in.ppm");
//
//            String[] fileNames = new String[]{"in.ppm", "out.jpg"};
//
//            PPMToJPG ppmToJPG = new PPMToJPG();
//
//            ppmToJPG.convert(fileNames);



//          Engine is removed .. so the code below is of no use now .. working on alternative way of solving this.
//        int width = 320;
//        int height = 200;
//        Vector camera = new Vector(0, 0, -1);
//
//        Material redMaterial = new Material(new Color(1.0, 0.0, 0.0));
//        Material greenMaterial = new Material(new Color(0.0, 1.0, 0.0));
//        Material blueMaterial = new Material(new Color(0.0, 0.0, 1.0));
//
//        List<Sphere> spheres = new ArrayList<>();
//        spheres.add(new Sphere(new Vector(0.0, 0.0, 1.0), 0.5, redMaterial)); // Sphere in front of the camera
//        spheres.add(new Sphere(new Vector(1.0, 0.0, 2.0), 0.5, greenMaterial)); // Sphere to the right
//        spheres.add(new Sphere(new Vector(-1.0, 0.0, 2.0), 0.5, blueMaterial)); // Sphere to the left
//
//
//        Engine engine = new Engine();
//        Image image = engine.render(scene);
//
//        image.createFile("output.ppm");

//        Image image = new Image(100, 200);
//
//        for(int i = 0;i<100;i++){
//            for(int j=0;j<200;j++){
//                double r = (double)i/100;
//                double g = (double)j/200;
//                double b = 0.2;
//
//                Color color = new Color(r,g,b);
//
//                image.setPixel(i,j,color);
//            }
//        }
//
//        image.createFile("transition_gradient.ppm");
//
//        String[] fileNames = new String[]{"transition_gradient.ppm", "transition_gradient.jpg"};
//
//        PPMToJPG ppmToJPG = new PPMToJPG();
//
