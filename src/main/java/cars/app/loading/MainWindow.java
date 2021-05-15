package cars.app.loading;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
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



    public static void main(String[] args) {
        MainWindow.launch();
    }


    @FXML
    public static Label l;

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
        stage.show();

        stage.setOnCloseRequest( event ->
        {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Выход из приложения");
            alert.setHeaderText("Сохранить данные?");
            alert.setContentText("При последующей загрузке объявления сохранятся");
            ButtonType buttonTypeSave = new ButtonType("Да");
            ButtonType buttonTypeCancel = new ButtonType("Отмена", ButtonBar.ButtonData.CANCEL_CLOSE);
            ButtonType buttonTypeNo = new ButtonType("Нет");
            alert.getButtonTypes().setAll(buttonTypeSave, buttonTypeNo,buttonTypeCancel);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == buttonTypeCancel) event.consume();
            else if (result.get() == buttonTypeSave) {
                try {
                    MarketPlace.save();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        });
    }

}

