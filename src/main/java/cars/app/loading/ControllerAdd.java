package cars.app.loading;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import cars.app.logic.*;


import java.io.File;
import java.io.IOException;

import static javafx.scene.paint.Color.GREEN;
import static javafx.scene.paint.Color.RED;

public class ControllerAdd {

    public Button buttonAdd;

    @FXML
    private ComboBox<String> brand;
    @FXML
    private TextField fieldModel;
    @FXML
    private TextField fieldYear;
    @FXML
    private TextField fieldPhone;
    @FXML
    private TextField fieldMileage;
    @FXML
    private TextField fieldPrice;


    @FXML
    private Label label;
    @FXML
    private Button close;
    @FXML
    private Button addPhoto;
    @FXML
    private TextField briefInfo;

    public File getImage() {
        return image;
    }

    private File image;

    public void setImage(File image) {
        this.image = image;
    }


    @FXML
    public void closeWindowAdd (ActionEvent event) throws IOException {
        Stage stage = (Stage) close.getScene().getWindow();
        stage.close();
        MainWindow mw = new MainWindow();
        mw.start(new Stage());
    }

    @FXML
    void newAuto(ActionEvent event) {

    }

    public void addPhotograph() {
        FileChooser fc = new FileChooser();
        File f = fc.showOpenDialog(new Stage());
        setImage(f);
    }

    @FXML
    protected void initialize() {
        brand.setEditable(true);
        brand.getItems().addAll("Марка", "Audi", "BMW", "Mercedes", "Toyota", "Volkswagen", "Volvo");
        brand.setValue("Марка");
    }

    public void addNewCar() {
        try {
            String brandOfCar = brand.getValue();
            String modelOfCar = fieldModel.getText();
            String yearOfCar = fieldYear.getText();
            String phoneOfOwner = fieldPhone.getText();
            String mileageOfCar = fieldMileage.getText();
            String priceOfCar = fieldPrice.getText();
            File img = getImage();
            String bi = briefInfo.getText();

            Car myCar = new Car(brandOfCar, modelOfCar, yearOfCar, phoneOfOwner, mileageOfCar, priceOfCar, img, bi);
            if (!brandOfCar.equals("") && !brandOfCar.equals("Марка") && !modelOfCar.equals("") &&
            (Integer.parseInt(yearOfCar) >= 1900 ||
            Integer.parseInt(yearOfCar) <= 2021) && Integer.parseInt(priceOfCar) >= 0 &&
            Integer.parseInt(mileageOfCar) >= 0 && Integer.parseInt(phoneOfOwner) > 0) {
                MarketPlace.addNewAd(myCar);
                brand.setValue("Марка");
                fieldModel.clear();
                fieldYear.clear();
                fieldPhone.clear();
                fieldMileage.clear();
                fieldPrice.clear();
                briefInfo.clear();

                label.setText("Объявление успешно добавлено!");
                label.setTextFill(GREEN);
            }
            else if (Integer.parseInt(yearOfCar) < 1900 || Integer.parseInt(yearOfCar) > 2021 || Integer.parseInt(phoneOfOwner) < 0
            || Integer.parseInt(priceOfCar) < 0 || Integer.parseInt(mileageOfCar) < 0) {
                label.setText("Введено некорректное значение!");
                label.setTextFill(RED);
            }
            else {
                label.setText("Заполните обязательные поля!");
                label.setTextFill(RED);
            }

        }
        catch (NumberFormatException n) {
            label.setText("Пробег, цена, телефон, год - целые числа! Заполните поля или исправьте значения");
            label.setTextFill(RED);
        }
    }
}
