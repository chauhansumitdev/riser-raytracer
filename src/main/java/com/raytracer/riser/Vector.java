package com.raytracer.riser;

import java.util.Random;

public class Vector {
    public double x;
    public double y;
    public double z;

    public Vector(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public String get_vector_values() {
        return x + " " + y + " " + z;
    }

    public double get_dot_product(Vector other) {
        return this.x * other.x + this.y * other.y + this.z * other.z;
    }

    public double get_magnitude() {
        return Math.sqrt(get_dot_product(this));
    }

    public Vector normalize() {
        double magnitude = get_magnitude();
        if (magnitude == 0) {
            System.err.println("Magnitude is zero. Cannot perform normalization.");
            return this;
        }
        return new Vector(this.x / magnitude, this.y / magnitude, this.z / magnitude);
    }

    public Vector add(Vector other) {
        return new Vector(this.x + other.x, this.y + other.y, this.z + other.z);
    }

    public Vector multiply_by_scalar(double value) {
        return new Vector(this.x * value, this.y * value, this.z * value);
    }

    public Vector subtract(Vector other) {
        return new Vector(this.x - other.x, this.y - other.y, this.z - other.z);
    }

    public Vector divide_by_scalar(double value) {
        if (value == 0) {
            throw new ArithmeticException("Cannot divide by zero.");
        }
        return new Vector(this.x / value, this.y / value, this.z / value);
    }

    public Color divide_by_scalar_clr(double value) {
        if (value == 0) {
            throw new ArithmeticException("Cannot divide by zero.");
        }
        return new Color(this.x / value, this.y / value, this.z / value);
    }

    public double length_squared() {
        return x * x + y * y + z * z;
    }

    public static Vector random_in_unit_sphere() {
        Random rand = new Random();
        Vector p;
        do {
            p = new Vector(rand.nextDouble() * 2.0 - 1.0, rand.nextDouble() * 2.0 - 1.0, rand.nextDouble() * 2.0 - 1.0);
        } while (p.length_squared() >= 1.0);
        return p;
    }
}
