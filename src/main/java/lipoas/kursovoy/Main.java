
package lipoas.kursovoy;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import lipoas.kursovoy.UI.MainController;
import lipoas.kursovoy.UI.RootLayoutController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Lazy;


import java.io.IOException;



@Lazy
@SpringBootApplication
public class Main extends AbstractJavaFxApplicationSupport {

    @Value("${ui.title:JavaFX приложение}")//
    private String windowTitle;

    @Autowired
    private ConfigurationControllers.View view;

    private Stage primaryStage;
    private BorderPane rootLayout;
    private MainController mainController;

    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle(windowTitle);
        primaryStage.setResizable(false);
        primaryStage.sizeToScene();
        initRootLayout();
        showLayout();
    }

    private void showLayout() {
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("views/Main.View"));
            AnchorPane myPane = loader.load();

            rootLayout.setCenter(myPane);
            mainController = loader.getController();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initRootLayout() {
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("views/RootLayout.View"));
            rootLayout = (BorderPane) loader.load();

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();

            RootLayoutController rootLayoutController = loader.getController();
            rootLayoutController.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
