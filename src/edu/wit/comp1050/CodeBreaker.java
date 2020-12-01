package edu.wit.comp1050;


import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;

public class CodeBreaker extends Pegs{ //Player guessing color combo
    private int lives; //number of times player can guess
    public Circle[] circles;
    public HBox[] boxes;

    public CodeBreaker(int num, String[] colorArray){
        super(num, colorArray);
    }

    //create initial "colorless" circles
    public Circle[] humCircles(){
        circles = new Circle[num];
        for(int i = 0; i < num; i++){
            circles[i] = Pegs.coloredPeg("white");
        }
        return circles;
    }
}