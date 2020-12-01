package edu.wit.comp1050;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class Controller {
    public VBox humanGuess;
    public HBox robox; //robot box
    public VBox verticalBox;
    public Label resultText;

    //default configs
    private int holes = 4;
    private boolean hidden = false;
    private boolean blanks = true;
    private boolean duplicates = false;
    private int[] lives = {8, 10, 12};

    public String[] colors = {"red", "blue", "green", "yellow", "pink", "purple", "black"};
    public CodeMaster robot = new CodeMaster(holes, colors, duplicates, blanks);
    public CodeBreaker human = new CodeBreaker(holes, colors);

    public Circle[] player = human.humCircles(); //stores "currently blank" player circles
    public Circle[] random = robot.createAIPegs(); //stores the answer circles
    public HBox[] someBoxes = new HBox[lives[0]]; //stores HBoxes with circles
    public Circle[] guesses = new Circle[holes]; //stores Circles that player guesses

    public int anchor = 0; //controls which circle
    public int vAnchor = 0; //controls HBox

    public void initialize(){
        //create AI circles
        for(int i = 0; i < random.length; i++){
            robox.getChildren().add(random[i]);
            if(hidden){
                Circle hiddenC = Pegs.coloredPeg("orange");
                robox.getChildren().set(i, hiddenC);
            }
        }

        //create human guesses
        for(int i = 0; i < someBoxes.length; i++){
            //create a new HBox
            HBox aBox = new HBox();
            aBox.setAlignment(Pos.CENTER);
            aBox.setSpacing(10);

            //Fill the HBox with circles
            for(Circle c : human.humCircles()){
                aBox.getChildren().add(c);
            }

            //Add it to the fxml
            humanGuess.getChildren().add(aBox);

            //put it in an array
            someBoxes[i] = aBox;
        }
        for(int i = 0; i < guesses.length; i++){
            //setup guesses so it isn't empty
            guesses[i] = Pegs.coloredPeg("white");
        }

    }

    public void buttonPressed(){
        Circle wowCircle = new Circle(20);
        wowCircle.setFill(Paint.valueOf("white"));
        anchor--;
        if(anchor <= -1)
            anchor = 0;
        someBoxes[vAnchor].getChildren().set(anchor, wowCircle);
    }

    public void skipClick(){
        if(checker())
            moveForward();
    }

    public void changeText(){
        if(vChecker()){
            if(checkWin()) {
                resultText.setText("You Win");
                robox.getChildren().setAll(random);
            }
            else if(!checkWin()){
                resultText.setText("Wrong");
                anchor = 0;
                if(vChecker()){
                    moveDown();
                    for(int i = 0; i < guesses.length; i++){
                        guesses[i] = Pegs.coloredPeg("white");
                    }
                }
                else{
                    resultText.setText("You have run out of lives");
                }
            }
        }
    }

    public void redCircle(){
        if(checker() && vChecker()){
            someBoxes[vAnchor].getChildren().set(anchor, Pegs.coloredPeg("red"));
            guesses[anchor] = Pegs.coloredPeg("red");
            moveForward();
        }
    }

    public void blueCircle(){
        if(checker() && vChecker()){
            someBoxes[vAnchor].getChildren().set(anchor, Pegs.coloredPeg("blue"));
            guesses[anchor] = Pegs.coloredPeg("blue");
            moveForward();
        }
    }

    public void greenCircle(){
        if(checker() && vChecker()){
            someBoxes[vAnchor].getChildren().set(anchor, Pegs.coloredPeg("green"));
            guesses[anchor] = Pegs.coloredPeg("green");
            moveForward();
        }
    }

    public void yellowCircle(){
        if(checker() && vChecker()){
            someBoxes[vAnchor].getChildren().set(anchor, Pegs.coloredPeg("yellow"));
            guesses[anchor] = Pegs.coloredPeg("yellow");
            moveForward();
        }
    }

    public void pinkCircle(){
        if(checker() && vChecker()){
            someBoxes[vAnchor].getChildren().set(anchor, Pegs.coloredPeg("pink"));
            guesses[anchor] = Pegs.coloredPeg("pink");
            moveForward();
        }

    }

    public void purpleCircle(){
        if(checker() && vChecker()){
            someBoxes[vAnchor].getChildren().set(anchor, Pegs.coloredPeg("purple"));
            guesses[anchor] = Pegs.coloredPeg("purple");
            moveForward();
        }

    }

    public void blackCircle(){
        if(checker() && vChecker()){
            someBoxes[vAnchor].getChildren().set(anchor, Pegs.coloredPeg("black"));
            guesses[anchor] = Pegs.coloredPeg("black");
            moveForward();
        }
    }

    public boolean checkWin(){
        int points = 0;
        for(int i = 0; i < holes; i++){
            if(guesses[i].getFill().equals(random[i].getFill())){
                points++;
            }
            else {
                for(int j = 0; j < holes; j++){
                    if(guesses[i].getFill().equals(random[j].getFill())){
                        guesses[i].setStrokeWidth(5);
                        guesses[i].setStroke(Paint.valueOf("aqua"));
                        someBoxes[vAnchor].getChildren().set(i, guesses[i]);
                        break;
                    }
                    else{
                        guesses[i].setStrokeWidth(5);
                        guesses[i].setStroke(Paint.valueOf("grey"));
                        someBoxes[vAnchor].getChildren().set(i, guesses[i]);
                    }
                }
            }
        }
        return points == holes;
    }

    //helper
    public boolean checker(){
        if(anchor < holes){
            return true;
        }
        return false;
    }

    public boolean vChecker(){
        if(vAnchor < someBoxes.length){
            return true;
        }
        return false;
    }

    public void moveForward(){
        anchor++;
    }

    public void moveDown(){
        vAnchor++;
    }
}
