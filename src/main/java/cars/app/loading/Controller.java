package cars.app.loading;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import cars.app.logic.Car;

import java.io.IOException;
import java.net.URL;

public class Controller {

    @FXML
    public Button closeButton;

    @FXML
    private Button add;

    @FXML
    private Button list;


    @FXML
    protected void initialize() {

    }


    public void startAddCarWindow() throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        URL url = getClass().getResource("/cars/app/addCarWindow.fxml");
        loader.setLocation(url);
        Pane root = loader.load();

        stage.setScene(new Scene(root));
        stage.show();

        Stage st = (Stage) add.getScene().getWindow();
        st.close();
    }

    public void startListCarWindow() throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        URL url = getClass().getResource("/cars/app/listCarWindow.fxml");
        loader.setLocation(url);
        Pane root = loader.load();

        Scene sc = new Scene(root);
        stage.setScene(sc);
        stage.show();

        Stage st = (Stage) add.getScene().getWindow();
        st.close();
    }

}

