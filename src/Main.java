import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent launcher = FXMLLoader.load(getClass().getResource("/view/Launcher.fxml"));

        primaryStage.setTitle("Game Launcher");
        primaryStage.setScene(new Scene(launcher));
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
