import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {
        /*
        Parent root = FXMLLoader.load(getClass().getResource("/view/TicTacToeGame.fxml"));
        primaryStage.setTitle("TicTacToe");
        */
        Parent root = FXMLLoader.load(getClass().getResource("/view/Connect4Game.fxml"));
        primaryStage.setScene(new Scene(root));

        primaryStage.setResizable(false);

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
