package lipoas.kursovoy;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import lipoas.kursovoy.UI.AddRefractoryController;
import lipoas.kursovoy.UI.MainController;
import lipoas.kursovoy.UI.RootLayoutController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

@Configuration
class ConfigurationControllers {

    /**
     * Autowired аннотация нужна для того, что бы спринг самостоятельно нашел
     * нужный bean и подставил его в наеш свойство/поле
     */
    @Autowired
    private Environment env;
    private AnchorPane mainLayout;
    private BorderPane rootLayout;

    /**
     * Аннотация Bean озночает, что это обычный объект, только он будет помещен в DI-контейнер Spring
     * Вообще все объекты в Spring - бины
     * DI-контейнер - хранилище бинов, которые их связывает. Служит для настройки зависимостей
     */

    /**
     * Создаем объект View для Root и Main Layout и отправляем его DI-контейнер
     * @return View
     * @throws IOException
     */
    /*@Bean(name = "mainView")
    public View getMainView() throws IOException {
        return loadView("View/Main.fxml");
    }*/
    @Bean(name = "mainView")
    public View getMainView() throws IOException {
        return loadViewWithRoot("View/RootLayout.fxml", "View/Main.fxml");
    }

    /**
     * Создаем объект View для Add layout и отправляем его DI-контейнер
     * @return View
     * @throws IOException
     */
    @Bean(name = "addView")
    public View getRefractoryView() throws IOException {
        return loadView("View/AddRefractoryLayout.fxml");
    }

    /**
     * Создаем объект DataSource для работы с базой данных, указываем наеш подключение и настройки для него,
     * и отправляем его DI-контейнер
     * @return View
     * @throws IOException
     */
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(Objects.requireNonNull(env.getProperty("spring.datasource.driver-class-name")));
        dataSource.setUrl(env.getProperty("spring.datasource.url"));
        dataSource.setUsername(env.getProperty("spring.datasource.username"));
        dataSource.setPassword(env.getProperty("spring.datasource.password"));

        //TODO: Delete println in master
        System.out.println("## DataSource1: " + dataSource);
        return dataSource;
    }

    /**
     * Именно благодаря этому методу мы добавили контроллер в контекст спринга,
     * и заставили его произвести все необходимые инъекции
     */
    @Bean
    public MainController getMainLayoutController() throws IOException {
        return (MainController) getMainView().getController();
    }

    @Bean
    public RootLayoutController getRootLayoutController() throws IOException {
        return (RootLayoutController) getMainView().getRootController();
    }

    @Bean
    public AddRefractoryController getRefractoryController() throws IOException {
        return (AddRefractoryController) getRefractoryView().getController();
    }

    /**
     * Самый обыкновенный способ использовать FXML загрузчик.
     * Как раз-таки на этом этапе будет создан объект-контроллер,
     * произведены все FXML инъекции и вызван метод инициализации контроллера.
     */
    public View loadView(String url) throws IOException {

        try{
            FXMLLoader loader = new FXMLLoader();
            InputStream fxmlStream = null;
            fxmlStream = getClass().getClassLoader().getResourceAsStream(url);
            mainLayout = loader.load(fxmlStream);
            return new View(loader.getRoot(), loader.getController());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Тотже способ использования FXML загрузчика, но здесь мы создаем и инициализируем
     * два контроллера Root и Main
     * @param urlRoot
     * @param urlSecondary
     * @return
     * @throws IOException
     */
    public View loadViewWithRoot(String urlRoot, String urlSecondary) throws IOException {

        try {
            FXMLLoader rootLoader = new FXMLLoader();
            FXMLLoader mainLoader = new FXMLLoader();
            InputStream fxmlStream = null;
            fxmlStream = getClass().getClassLoader().getResourceAsStream(urlRoot);
            rootLayout = rootLoader.load(fxmlStream);

            fxmlStream = getClass().getClassLoader().getResourceAsStream(urlSecondary);
            mainLayout = mainLoader.load(fxmlStream);
            rootLayout.setCenter(mainLayout);

            return new View(rootLoader.getRoot(), rootLoader.getController(), mainLoader.getController());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Класс - оболочка: контроллер мы обязаны указать в качестве бина,
     * а view - представление, нам предстоит использовать в точке входа {@link Application}.
     */
    public class View {
        private Parent view;
        private Object mainController;
        private Object rootController;

        public View(Parent view, Object mainController) {
            this.view = view;
            this.mainController = mainController;
        }

        /**
         * Дополнительный конструктор: возвращает два контроллера, вместо одного
         * Для Root и Main Layout
         * @param view
         * @param rootController
         * @param mainController
         */
        public View(Parent view, Object rootController, Object mainController) {
            this.view = view;
            this.mainController = mainController;
            this.rootController = rootController;
        }

        public Parent getView() {
            return view;
        }

        public void setView(Parent view) {
            this.view = view;
        }

        public Object getController() {
            return mainController;
        }

        public void setController(Object controller) {
            this.mainController = controller;
        }

        public Object getRootController() {
            return rootController;
        }

        public void setRootController(Object rootController) {
            this.rootController = rootController;
        }
    }

}