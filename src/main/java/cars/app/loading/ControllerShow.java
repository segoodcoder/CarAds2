package cars.app.loading;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import cars.app.logic.*;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.Map;

import static cars.app.loading.MainWindow.USER;
import static javafx.scene.paint.Color.RED;

public class ControllerShow {
    @FXML
    ScrollPane sp;
    @FXML
    HBox hbl;
    @FXML
    Label brand;
    @FXML
    Label model;
    Label year;
    Label phone;
    Label mileage;
    Label price;
    Label briefInfo;
    ImageView ig;
    Label hashCode;

    @FXML
    private Button back;

    public void initialize() throws IOException {
        try {
            final VBox fp = new VBox();
            MarketPlace.loadListOfCars();
            Map<Integer, Car> ll = MarketPlace.getListOfCars();
            for (Map.Entry<Integer, Car> entry : ll.entrySet()) {

                Car car = entry.getValue();

                hbl = new HBox();
                hbl.setStyle("-fx-padding: 10;" +
                        "-fx-border-style: solid inside;" +
                        "-fx-border-width: 0.5;" +
                        "-fx-border-insets: 5;" +
                        "-fx-border-color: black;");

                hashCode = new Label("Код в базе: \n" + entry.getKey().toString());
                hashCode.setFont(Font.font("Arial", FontWeight.BOLD, 12));
                hashCode.setAlignment(Pos.CENTER);
                hashCode.setMinSize(100, 40);
                hashCode.setMaxSize(200, 40);

                ig = new ImageView();
                ig.setFitHeight(100);
                ig.setFitWidth(200);

                brand = new Label(car.getBrand() + " ");
                brand.setFont(Font.font("Arial", FontWeight.BOLD, 12));
                brand.setAlignment(Pos.CENTER);
                brand.setMinSize(100, 20);
                brand.setMaxSize(200, 20);

                model = new Label(car.getModel() + " ");
                model.setFont(Font.font("Arial", FontWeight.BOLD, 12));
                model.setAlignment(Pos.CENTER);
                model.setMinSize(100, 20);
                model.setMaxSize(200, 20);

                year = new Label(car.getYear() + " год ");
                year.setFont(Font.font("Arial", 12));
                year.setAlignment(Pos.CENTER);
                year.setMinSize(100, 20);
                year.setMaxSize(200, 20);

                phone = new Label(" Телефон: " + car.getPhone() + " ");
                phone.setFont(Font.font("Arial", 12));
                phone.setAlignment(Pos.CENTER);
                phone.setMinSize(100, 20);
                phone.setMaxSize(200, 20);

                mileage = new Label(car.getMileage() + " км ");
                mileage.setFont(Font.font("Arial", 12));
                mileage.setAlignment(Pos.CENTER);
                mileage.setMinSize(100, 20);
                mileage.setMaxSize(200, 20);

                price = new Label("Цена: " + car.getPrice() + " руб");
                price.setFont(Font.font("Arial", FontWeight.BOLD, 12));
                price.setTextFill(RED);
                price.setAlignment(Pos.CENTER);
                price.setMinSize(100, 20);
                price.setMaxSize(200, 20);

                if (car.getImage() != null) {
                    ig.setImage(new Image(car.getImage().toURI().toString()));
                } else {
                    ig.setImage(new Image(new File("src/main/java/org.example.pictures/stockImage.jpg").toURI().toString()));
                }

                briefInfo = new Label("\n Краткая информация: \n" + car.getBriefInfo());
                briefInfo.setWrapText(true);

                hbl.getChildren().addAll(hashCode, brand, model, year, phone, mileage, price, ig, briefInfo);
                fp.getChildren().add(hbl);

            }
            sp.setContent(fp);
            sp.setPannable(true);
        } catch (NullPointerException ignored) {

        }
    }

    public void goBack() throws IOException {
        Stage stage = MainWindow.getpStage();
        FXMLLoader loader = new FXMLLoader();
        URL url = getClass().getResource("/cars/app/mainWindow.fxml");
        loader.setLocation(url);
        Pane root = loader.load();
        root.getChildren().add(new Label(USER));
        Scene sc = new Scene(root);
        stage.setScene(sc);
        stage.show();
    }
}
