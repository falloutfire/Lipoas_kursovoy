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
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;

@Lazy
@SpringBootApplication
public class Application extends AbstractJavaFxApplicationSupport {

    private Stage primaryStage;
    private AnchorPane rootLayout;
    //MainController mainController = null;

    @Value("${ui.title:JavaFX приложение}")//
    private String windowTitle;

    @Autowired
    @Qualifier("mainView")
    private ConfigurationControllers.View viewMain;

    @Autowired
    @Qualifier("addView")
    private ConfigurationControllers.View viewAdd;

    @Autowired
    private ApplicationContext appContext;

    public static void main(String[] args) {
        launchApp(Application.class, args);
    }

    // ЗДесь думаю сам разберешься че за Стайдж и че с ним делают :)
    @Override
    public void start(Stage stage) throws Exception {
        this.primaryStage = stage;
        this.primaryStage.setTitle(windowTitle);
        this.primaryStage.setScene(new Scene(viewMain.getView()));
        this.primaryStage.setResizable(false);
        this.primaryStage.centerOnScreen();
        this.primaryStage.show();

        /**
         * Получаем из контекста bean Main Controller и вызываем его метод setApp()
         * В этот мометн кстати к FXML уже привязан контроллер
         * это можно увидеть в логах
         * (при запуске появится lipoas.kursovoy.UI.MainController@(какие-то числа)
         * а потом когда мы из контекста вытащим наш контроллер и вызовем setApp()
         * то снова появится тот же lipoas.kursovoy.UI.MainController@(какие-то числа) + test :)
         */
        MainController mainController = appContext.getBean(MainController.class);
        mainController.setApp(this);
    }

    public void showLayoutAdd() {
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Добавить");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(this.primaryStage);
        Scene scene = new Scene(viewAdd.getView());
        dialogStage.setScene(scene);
        dialogStage.setResizable(false);
        dialogStage.centerOnScreen();
        dialogStage.showAndWait();
    }

}