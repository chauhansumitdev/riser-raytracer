package com.raytracer.riser;

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
}
