import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppInitializer extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent parent = FXMLLoader.load(this.getClass().getResource("/lk/ijse/wholeSalePos/view/MainForm.fxml"));
        Scene mainScene = new Scene(parent);
        primaryStage.setScene(mainScene);
        primaryStage.setTitle("Wholesale POS");
        primaryStage.centerOnScreen();
        primaryStage.show();
    }
}
