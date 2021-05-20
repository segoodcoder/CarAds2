package cars.app.loading;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

import static javafx.scene.paint.Color.RED;

public class Controller {


    @FXML
    Label lbl;

    @FXML
    Button account;

    @FXML
    protected void initialize() {

    }

    public void startAddCarWindow() throws NullPointerException, IOException {
        try {
            if (!MainWindow.getUser().equals("")) {
                FXMLLoader loader = new FXMLLoader();
                URL url = getClass().getResource("/cars/app/addCarWindow.fxml");
                loader.setLocation(url);
                Pane root = loader.load();

                Stage stage = MainWindow.getpStage();
                stage.setResizable(false);

                stage.setScene(new Scene(root));
                stage.show();
            }

        } catch (NullPointerException ignored) {
            lbl.setText("Сначала зарегистрируйтесь или войдите!");
            lbl.setTextFill(RED);
        }
    }

    public void startListCarWindow() throws IOException {
        Stage stage = MainWindow.getpStage();
        stage.setMinHeight(400);
        stage.setMinWidth(600);
        FXMLLoader loader = new FXMLLoader();
        URL url = getClass().getResource("/cars/app/listCarWindow.fxml");
        loader.setLocation(url);
        Pane root = loader.load();

        Scene sc = new Scene(root);
        stage.setScene(sc);
        stage.show();

    }

    public void showAccount() throws IOException {

        Stage st = MainWindow.getpStage();
        st.setResizable(false);

        FXMLLoader loader = new FXMLLoader();
        URL url = getClass().getResource("/cars/app/account.fxml");
        loader.setLocation(url);
        AnchorPane root = loader.load();

        Scene sc = new Scene(root);
        st.setScene(sc);
        st.show();

    }

}

