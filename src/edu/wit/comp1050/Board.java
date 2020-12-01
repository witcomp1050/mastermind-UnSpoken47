package edu.wit.comp1050;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;

public class Board {
    CodeMaster computer;
    CodeBreaker player;

    //default configs
    private int holes = 4;
    private boolean hidden = false;
    private boolean blanks = true;
    private boolean duplicates = false;
    private int lives = 8;

    public Board(CodeMaster computer, CodeBreaker player){
        this.computer = computer;
        this.player = player;
    }

    public void createBoard(VBox gameSpace, VBox guessSpace, HBox robotAns, String[] colorPallet, Circle[] botPegs, Circle[] playerPegs, HBox[] lifeBox, Circle[] guesses){
        computer = new CodeMaster(holes, colorPallet, duplicates, blanks);
        player = new CodeBreaker(holes, colorPallet);
        botPegs = computer.createAIPegs();
        playerPegs = player.humCircles();
        lifeBox = new HBox[holes];

        for(int i = 0; i < botPegs.length; i++){
            robotAns.getChildren().add(botPegs[i]);
            if(hidden){
                Circle hiddenC = Pegs.coloredPeg("orange");
                robotAns.getChildren().set(i, hiddenC);
            }
        }
        for(int i = 0; i < lifeBox.length; i++){
            HBox aBox = new HBox();
            aBox.setAlignment(Pos.CENTER);
            aBox.setSpacing(10);
            for(Circle c : player.humCircles()){
                aBox.getChildren().add(c);
            }
            guessSpace.getChildren().add(aBox);
            lifeBox[i] = aBox;
        }
        for(int i = 0; i < guesses.length; i++){
            guesses[i] = Pegs.coloredPeg("white");
        }
    }
}
