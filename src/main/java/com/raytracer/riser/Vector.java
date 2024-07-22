package com.raytracer.riser;

public class Vector {
    public double x;
    public double y;
    public double z;

    public Vector(double x, double y, double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public String get_vector_values(){
        return x + " " + y + " " + z;
    }

    public double get_dot_product(Vector other){
        return this.x * other.x + this.y * other.y + this.z * other.z;
    }

    public double get_magnitude(){
        return Math.sqrt(get_dot_product(this));
    }

    public Vector normalize(){
        double magnitude = get_magnitude();
        if (magnitude == 0) {
            System.err.println("Magnitude is zero. Cannot perform normalization.");
            return this;
        }
        return new Vector(this.x / magnitude, this.y / magnitude, this.z / magnitude);
    }

    public Vector add(Vector other){
        return new Vector(this.x + other.x, this.y + other.y, this.z + other.z);
    }

    public Vector mul_by_scalar(double value){
        return new Vector(this.x * value, this.y * value, this.z * value);
    }

    public Vector subtract(Vector other){
        return new Vector(this.x - other.x, this.y - other.y, this.z - other.z);
    }

    public Vector div_by_scalar(double value){
        if (value == 0) {
            throw new ArithmeticException("Cannot divide by zero.");
        }
        return new Vector(this.x / value, this.y / value, this.z / value);
    }

//    public static void main(String[] args) {
//        Vector v1 = new Vector(3.0, 4.0, 0.0);
//        Vector v2 = new Vector(1.0, 2.0, 2.0);
//
//        System.out.println("Vector v1: " + v1.get_vector_values());
//        System.out.println("Vector v2: " + v2.get_vector_values());
//
//        Vector v3 = v1.add(v2);
//        System.out.println("v1 + v2: " + v3.get_vector_values());
//
//        Vector v4 = v1.subtract(v2);
//        System.out.println("v1 - v2: " + v4.get_vector_values());
//
//        Vector v5 = v1.mul_by_scalar(2);
//        System.out.println("v1 * 2: " + v5.get_vector_values());
//
//        Vector v6 = v1.div_by_scalar(2);
//        System.out.println("v1 / 2: " + v6.get_vector_values());
//
//        System.out.println("Dot product v1 . v2: " + v1.get_dot_product(v2));
//        System.out.println("Magnitude of v1: " + v1.get_magnitude());
//
//        Vector normalizedV1 = v1.normalize();
//        System.out.println("Normalized v1: " + normalizedV1.get_vector_values());
//    }
}
