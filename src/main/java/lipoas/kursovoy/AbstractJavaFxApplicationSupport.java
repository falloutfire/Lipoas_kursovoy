package lipoas.kursovoy;

import javafx.application.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;


/**
 * Настраиваем одновременную работу Spring и JavaFX
 * Вязли класс Appliaction из JavaFX и переопределили его методы init() и stop()
 * Связали контексты FX и Spring (если правилно понял, то ConfigurableApplicationContext -
 * это интерфейс расширяющий BeanFactory (BeanFactory — основной интерфейс DI-контейнера),
 * ну или само приложение спринг если в лоб (но это неточно :) ))
 */
public abstract class AbstractJavaFxApplicationSupport extends Application {

    private static String[] savedArgs;

    protected ConfigurableApplicationContext context;

    @Override
    public void init() throws Exception {
        context = SpringApplication.run(getClass(), savedArgs);
        context.getAutowireCapableBeanFactory().autowireBean(this);
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        context.close();
    }

    protected static void launchApp(Class<? extends AbstractJavaFxApplicationSupport> clazz, String[] args) {
        AbstractJavaFxApplicationSupport.savedArgs = args;
        Application.launch(clazz, args);
    }
}