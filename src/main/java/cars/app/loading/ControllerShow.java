package cars.app.loading;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import cars.app.logic.*;


import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

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

    @FXML
    private Button back;

    public void initialize() throws IOException {
        try {
            final VBox fp = new VBox();
            LinkedList<Car> ll = MarketPlace.loadListOfCars();
            for (Car car : ll) {
                hbl = new HBox();
                ig = new ImageView();
                ig.setFitHeight(100);
                ig.setFitWidth(200);
                brand = new Label(car.getBrand() + " ");
                brand.setFont(Font.font("Arial", FontWeight.BOLD, 12));
                model = new Label(car.getModel() + " ");
                model.setFont(Font.font("Arial", FontWeight.BOLD, 12));
                year = new Label(car.getYear() + " год ");
                year.setFont(Font.font("Arial", 12));
                phone = new Label(" Телефон: " + car.getPhone() + " ");
                phone.setFont(Font.font("Arial", 12));
                mileage = new Label(car.getMileage() + " км ");
                mileage.setFont(Font.font("Arial", 12));
                price = new Label("Цена: " + car.getPrice() + " руб");
                price.setFont(Font.font("Arial", FontWeight.BOLD, 12));
                price.setTextFill(RED);
                if (car.getImage() != null) {
                    ig.setImage(new Image(car.getImage().toURI().toString()));
                } else {
                    ig.setImage(new Image(new File("src/main/java/org.example.pictures/stockImage.jpg").toURI().toString()));
                }
                briefInfo = new Label("\n Краткая информация: " + car.getBriefInfo());
                hbl.getChildren().addAll(brand, model, year, phone, mileage, price, ig, briefInfo);
                fp.getChildren().add(hbl);

            }
            sp.setContent(fp);
            sp.setPannable(true);
        } catch (NullPointerException n) {
            Label noAds = new Label("Объявлений пока нет!");
            noAds.setTextFill(RED);
            MainWindow.l = noAds ;
        }
    }

    public void goBack() throws IOException {
        Stage sc = (Stage) back.getScene().getWindow();
        sc.close();
        MainWindow mw = new MainWindow();
        mw.start(new Stage());
    }
}
