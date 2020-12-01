package edu.wit.comp1050;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class Pegs{
    protected int num;
    public String[] colorArray;

    public Pegs(int num, String[] colorArray){
        this.num = num;
        this.colorArray = colorArray;
    }

    public static Circle createPeg(){
        return new Circle(20);
    }

    public static Circle coloredPeg(String color){
        Circle coloredCircle = new Circle(20);
        coloredCircle.setFill(Paint.valueOf(color));
        return coloredCircle;
    }

    public static Paint getColor(Circle circle){
        return circle.getFill();
    }

    public Paint randomColor(){
        int randomNum = (int)(Math.random() * colorArray.length);
        return Paint.valueOf(colorArray[randomNum]);
    }


}
