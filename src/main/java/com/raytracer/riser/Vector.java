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

    public String getVectorValues() {
        return x + " " + y + " " + z;
    }

    public double getDotProduct(Vector other) {
        return this.x * other.x + this.y * other.y + this.z * other.z;
    }

    public double getMagnitude() {
        return Math.sqrt(getDotProduct(this));
    }

    public Vector normalize() {
        double magnitude = getMagnitude();
        if (magnitude == 0) {
            System.err.println("Magnitude is zero. Cannot perform normalization.");
            return this;
        }
        return new Vector(this.x / magnitude, this.y / magnitude, this.z / magnitude);
    }

    public Vector add(Vector other) {
        return new Vector(this.x + other.x, this.y + other.y, this.z + other.z);
    }

    public Vector multiplyByScalar(double value) {
        return new Vector(this.x * value, this.y * value, this.z * value);
    }

    public Vector subtract(Vector other) {
        return new Vector(this.x - other.x, this.y - other.y, this.z - other.z);
    }

    public Vector divideByScalar(double value) {
        if (value == 0) {
            throw new ArithmeticException("Cannot divide by zero.");
        }
        return new Vector(this.x / value, this.y / value, this.z / value);
    }
}
