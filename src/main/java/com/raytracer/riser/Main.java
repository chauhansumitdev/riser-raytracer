package com.raytracer.riser;


// testing image files and other stuffs .. this probably wont be the project main executable file .. search for App.java for executing the project
// this is for TESTINGGGGG>>>>>>>>>>>>>>>>>>............


public class Main {
    public static void main(String[] args) {

//        Color red = new Color(1,0,0);
//        Color green = new Color(0,1,0);
//        Color blue = new Color(0,0,1);
//
//        Image image = new Image(1,3);
//        image.set_pixel(0,0,red);
//        image.set_pixel(0,1,green);
//        image.set_pixel(0,2,blue);
//
//        image.create_file("test_one.ppm");

        // making a blue gradient;

        Image image = new Image(100, 100);

        double value = 0.01;

        for(int i=0;i<100;i++){
            for(int j=0;j<100;j++){
                Color color = new Color(0,value,0);
                image.set_pixel(i,j,color);
            }
            value+=0.01;
        }

        image.create_file("green_gradient.ppm");

    }
}
