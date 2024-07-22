package com.raytracer.riser;

public class Color extends Vector {

    public Color(double r, double g, double b) {
        super(r, g, b);
    }

    public String getColorValues() {
        return "R: " + x + ", G: " + y + ", B: " + z;
    }

    public static Color fromHex(String hex) {
        int r = Integer.valueOf(hex.substring(1, 3), 16);
        int g = Integer.valueOf(hex.substring(3, 5), 16);
        int b = Integer.valueOf(hex.substring(5, 7), 16);
        return new Color(r / 255.0, g / 255.0, b / 255.0);
    }

    public Color add(Color other) {
        return new Color(this.x + other.x, this.y + other.y, this.z + other.z);
    }
}
