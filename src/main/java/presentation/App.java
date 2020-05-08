package presentation;

import domain.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import persistence.DatabaseManager;

import java.io.IOException;

public class App extends Application {

    private static Scene scene;
    private static DatabaseManager databaseManager = new DatabaseManager();
    private static UserManager userManager = new UserManager();
    private static AuthentificationManager authentificationManager = new AuthentificationManager();
    private static ProductionManager productionManager = new ProductionManager();
    private static CreditManager creditManager = new CreditManager();
    private static NotificationManager notificationManager = new NotificationManager();

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("Start"));
        stage.setScene(scene);
        stage.setTitle("AJATE Credit Management");
        stage.setResizable(false);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static UserManager getUserManager() {
        return userManager;
    }

    public static AuthentificationManager getAuthentificationManager() {
        return authentificationManager;
    }

    public static ProductionManager getProductionManager() {
        return productionManager;
    }

    public static CreditManager getCreditManager() {
        return creditManager;
    }

    public static DatabaseManager getDatabaseManager() {
        return databaseManager;
    }

    public static NotificationManager getNotificationManager() {
        return notificationManager;
    }

    public static void main(String[] args) {
        launch();
    }
}