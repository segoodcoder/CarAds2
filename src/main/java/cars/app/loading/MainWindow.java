package cars.app.loading;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import cars.app.logic.MarketPlace;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;

public class MainWindow extends Application {


    public static String getUSER() {
        return USER;
    }

    public static void setUSER(String USER) {
        MainWindow.USER = USER;
    }

    public static String USER;

    public static void main(String[] args) {
        MainWindow.launch();
    }

    public static Stage getpStage() {
        return pStage;
    }

    public static void setpStage(Stage pStage) {
        MainWindow.pStage = pStage;
    }


    private static Stage pStage;



    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        URL url = getClass().getResource("/cars/app/mainWindow.fxml");
        loader.setLocation(url);
        Pane root = loader.load();


        Hyperlink vk = new Hyperlink("Created by Georgy");
        vk.setLayoutX(46);
        vk.setLayoutY(350);
        vk.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                getHostServices().showDocument("https://vk.com/neweratoday");
            }
        });

        root.getChildren().addAll(vk);

        Scene mainScene = new Scene(root);

        stage.setScene(mainScene);
        stage.setTitle("Добро пожаловать!");
        setpStage(stage);
        stage.show();


        stage.setOnCloseRequest( event ->
        {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Выход из приложения");
            alert.setHeaderText("Вы хотите выйти?");
            ButtonType buttonTypeSave = new ButtonType("Да");
            ButtonType buttonTypeCancel = new ButtonType("Отмена", ButtonBar.ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().setAll(buttonTypeSave,buttonTypeCancel);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == buttonTypeCancel) event.consume();
        });
    }

}

