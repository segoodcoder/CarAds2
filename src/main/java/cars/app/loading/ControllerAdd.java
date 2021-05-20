package cars.app.loading;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import cars.app.logic.*;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static cars.app.loading.MainWindow.USER;
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
        Stage stage = MainWindow.getpStage();
        MarketPlace.saveCarsFalse();
        FXMLLoader loader = new FXMLLoader();
        URL url = getClass().getResource("/cars/app/mainWindow.fxml");
        loader.setLocation(url);
        Pane root = loader.load();
        root.getChildren().add(new Label(USER));
        Scene sc = new Scene(root);
        stage.setScene(sc);
        stage.show();

    }

    @FXML
    void newAuto(ActionEvent event) {

    }

    public void addPhotograph() throws IOException {
        FileChooser fc = new FileChooser();
        File f = fc.showOpenDialog(new Stage());
        String basePath = f.getPath();
        String name = f.getName();

        moveFile(basePath, "cache/photos/" + name);

        File pict = new File("cache/photos/" + name);
        setImage(pict);
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
            int yearOfCar = Integer.parseInt(fieldYear.getText());
            long phoneOfOwner = Long.parseLong(fieldPhone.getText());
            int mileageOfCar = Integer.parseInt(fieldMileage.getText());
            int priceOfCar = Integer.parseInt(fieldPrice.getText());
            File img = getImage();
            String bi = briefInfo.getText();

            Car myCar = new Car(brandOfCar, modelOfCar, yearOfCar, phoneOfOwner, mileageOfCar, priceOfCar, img, bi);
            if (!brandOfCar.equals("") && !brandOfCar.equals("Марка") && !modelOfCar.equals("") &&
            yearOfCar >= 1900 && yearOfCar <= 2021 && priceOfCar >= 0 &&
            mileageOfCar >= 0 && phoneOfOwner >= 89000000000L && phoneOfOwner <= 89999999999L) {

                MarketPlace.addNewAd(myCar);
                MarketPlace.updateUserToAdvert(myCar);

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
            else if (yearOfCar < 1900 || yearOfCar > 2021 || phoneOfOwner < 0
            || priceOfCar < 0 || mileageOfCar < 0) {
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

    private static void moveFile(String src, String dest ) {
        Path result = null;
        try {
            result =  Files.copy(Paths.get(src), Paths.get(dest));
        } catch (IOException e) {
            System.out.println("Exception while moving file: " + e.getMessage());
        }
    }
}


