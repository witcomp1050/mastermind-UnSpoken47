package edu.wit.comp1050;

import javafx.scene.shape.Circle;
import java.util.ArrayList;


public class CodeMaster extends Pegs{ //Computer making a combination of colors
    public Circle[] array;
    private ArrayList<String> colors = new ArrayList<>();
    private boolean dupes;
    private boolean blanks;

    public CodeMaster(int num, String[] colorArray, boolean dupes, boolean blanks){
        super(num, colorArray);
        this.dupes = dupes;
        this.blanks = blanks;
    }

    public Circle[] createAIPegs(){ //Creates randomized pegs for the guesser
        if(blanks){
            colors.add("white");
        }
        for(int i = 0; i < colorArray.length; i++){
            colors.add(colorArray[i]);
        }
        array = new Circle[num];
        if(!dupes){
            createNoDupe();
        }
        else{
            for(int i = 0; i < num; i++){
                array[i] = Pegs.coloredPeg(randomColor().toString());
            }
        }
        return array;
    }

    private Circle[] createNoDupe(){ //Creates random pegs without duplicate colors
        array = new Circle[num];
        for(int i = 0; i < num; i++){
            String randomC = randomNum(colors);
            array[i] = Pegs.coloredPeg(randomC);
            colors.remove(randomC);
        }
        return array;
    }

    private String randomNum(ArrayList array){ //Chooses a random Color
        int newNum = (int)(Math.random() * array.size());
        return colors.get(newNum);
    }

    public void listColors(String[] arr){ //Helper
        for(int i = 0; i < arr.length; i++){
            System.out.println(arr[i]);
        }

    }
}
