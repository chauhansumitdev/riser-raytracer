package com.raytracer.riser;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Image {

    int cols;
    int rows;

    int[][][] image;

    public Image(int rows, int cols){
        this.rows = rows;
        this.cols = cols;

        image = new int[rows][cols][3];
    }

    public void set_pixel(int x, int y, Color color){
        double x_curr = color.x;
        double y_curr = color.y;
        double z_curr = color.z;

        int red = (int) Math.round(Math.max(Math.min(x_curr * 255, 255), 0));
        int green = (int) Math.round(Math.max(Math.min(y_curr * 255, 255), 0));
        int blue = (int) Math.round(Math.max(Math.min(z_curr * 255, 255), 0));

        image[x][y][0] = red;
        image[x][y][1] = green;
        image[x][y][2] = blue;
    }

    public void create_file(String file_name){
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file_name))){

            bufferedWriter.write("P3\n");
            bufferedWriter.write(cols+" "+rows+"\n");
            bufferedWriter.write("255\n");

            for(int i=0;i<rows;i++){
                for(int j=0;j<cols;j++){

                    for(int k=0;k<3;k++){
                       bufferedWriter.write(image[i][j][k]+" ");
                    }
                }
                bufferedWriter.write("\n");
            }

        }catch(IOException e){
            e.printStackTrace();
        }
    }

}
