package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class GameLaunchController{

    //private String ticTacToe = "/view/TicTacToeGame.fxml";
    //private String connect4 = "/view/Connect4Game.fxml";


    public void changeScene(String fxml, String gameTitle){
        try {
            Parent gameWindow;
            Stage gameStage;

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxml));

            gameWindow = fxmlLoader.load();

            gameStage = new Stage();
            gameStage.setScene(new Scene(gameWindow));

            gameStage.setTitle(gameTitle);
            gameStage.setResizable(false);
            gameStage.show();

        } catch (Exception e){
            System.out.println("Something fucked up");
            e.printStackTrace();
        }
    }


    @FXML
    public void startTicTacToe(){
        changeScene("/view/TicTacToeGame.fxml", "Tic Tac Toe");
    }

    @FXML
    public void startConnect4(){
        changeScene("/view/Connect4Game.fxml", "Connect 4");
    }
}
