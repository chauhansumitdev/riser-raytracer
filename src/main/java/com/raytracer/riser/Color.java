package com.raytracer.riser;

public class Color extends Vector {

    public Color(double r, double g, double b) {
        super(r, g, b);
    }

    public String get_color_values() {
        return "R: " + x + ", G: " + y + ", B: " + z;
    }
}
