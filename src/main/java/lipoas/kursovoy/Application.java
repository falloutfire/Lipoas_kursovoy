package lipoas.kursovoy;


import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lipoas.kursovoy.UI.MainController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Lazy;

@Lazy
@SpringBootApplication
public class Application extends AbstractJavaFxApplicationSupport {

    private Stage primaryStage;
    private AnchorPane rootLayout;

    @Value("${ui.title:JavaFX приложение}")//
    private String windowTitle;

    @Qualifier("mainView")
    @Autowired
    private ConfigurationControllers.View viewMain;

    MainController mainController = null;

    @Qualifier("addView")
    @Autowired
    private ConfigurationControllers.View viewAdd;

    public static void main(String[] args) {
        launchApp(Application.class, args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.primaryStage = stage;
        this.primaryStage.setTitle(windowTitle);
        this.primaryStage.setScene(new Scene(viewMain.getView()));
        this.primaryStage.setResizable(false);
        this.primaryStage.centerOnScreen();
        this.primaryStage.show();
        mainController = (MainController) viewMain.getController();
        mainController.setApp(this);
    }

    public void showLayoutAdd() {
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Добавить");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(primaryStage);
        Scene scene = new Scene(viewAdd.getView());
        dialogStage.setScene(scene);
        dialogStage.setResizable(false);
        dialogStage.centerOnScreen();
        dialogStage.showAndWait();
    }

}