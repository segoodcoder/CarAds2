package cars.app.loading;

import cars.app.logic.BaseOfUsers;
import cars.app.logic.Car;
import cars.app.logic.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;

import static cars.app.loading.MainWindow.USER;
import static javafx.scene.paint.Color.GREEN;
import static javafx.scene.paint.Color.RED;

import cars.app.logic.MarketPlace;

public class ControllerAcc {
    @FXML
    Button exit;

    @FXML
    TextField login;

    @FXML
    PasswordField password;

    @FXML
    Button signUp;

    @FXML
    Button signIn;

    @FXML
    Label lc = new Label("=)");

    @FXML
    Label bigLabel;

    @FXML
    TextField forDelete;

    @FXML
    TextField ta;

    public void goBack() throws IOException {

        FXMLLoader loader = new FXMLLoader();
        URL url = getClass().getResource("/cars/app/mainWindow.fxml");
        loader.setLocation(url);
        Pane root = loader.load();
        root.getChildren().add(new Label(USER));
        Scene sc = new Scene(root);

        Stage p = MainWindow.getpStage();
        p.setScene(sc);

        BaseOfUsers.listOfUsersToMemory();

        p.show();
    }

    public void addNewUser() throws IOException {

        String newLogin = login.getText();
        String newPassword = password.getText();
        User newUser = new User(newLogin, newPassword);

        BaseOfUsers.loadListOfUsers();
        Map<String, String> m = BaseOfUsers.getListOfUsers();

        if (m.containsKey(newLogin)) {
            login.clear();
            password.clear();
            lc.setText("Такой пользователь уже существует!");
            lc.setTextFill(RED);
        }

        else {
            m.put(newLogin, newPassword);
            BaseOfUsers.setListOfUsers(m);
            login.clear();
            password.clear();
            lc.setText("Вы успешно зарегистрированы!");
            lc.setTextFill(GREEN);
        }
        lc.setWrapText(true);

    }

    public void logInto() throws IOException {

        BaseOfUsers.loadListOfUsers();

        String username = login.getText();
        if (BaseOfUsers.getListOfUsers().containsKey(username)) {
            if (!BaseOfUsers.getListOfUsers().get(username).equals(password.getText())) {
                lc.setText("Неверный пароль!");
                lc.setTextFill(RED);
            } else {
                try {
                    lc.setText("Вы вошли как " + username);
                    lc.setTextFill(GREEN);
                    MainWindow.setUSER(username);
                    MarketPlace.loadUserToAdvert();
                    Map<String, ArrayList<Integer>> m = MarketPlace.getUserToAdvert();
                    ArrayList<Integer> l = m.get(username);
                    ArrayList<String> s = new ArrayList<>();
                    for (Integer elem : l) {
                        s.add(elem.toString());
                    }
                    ta.setText(s.toString());
                } catch (NullPointerException ignored) {}
            }
        }
        else {
            lc.setText("Имя пользователя не найдено!");
            lc.setTextFill(RED);
        }
        login.clear();
        password.clear();


    }

    public void removeFromBase() throws IOException {

        MarketPlace.loadUserToAdvert();
        MarketPlace.loadListOfCars();

        Map<Integer, Car> tempCars = MarketPlace.getListOfCars();
        Map<String, ArrayList<Integer>> tempUsers = MarketPlace.getUserToAdvert();

        String user = MainWindow.getUSER();
        Integer id = Integer.valueOf(forDelete.getText());
            if (tempUsers.get(user).contains(id)) {

                ArrayList<Integer> tempList = tempUsers.get(user);

                tempList.remove(id);
                tempUsers.put(user, tempList);
                tempCars.remove(id);

                MarketPlace.setListOfCars(tempCars);
                MarketPlace.setUserToAdvert(tempUsers);

                MarketPlace.saveCarsFalse();
            }
            else {
                bigLabel.setText("Похоже, это не ваше объявление!");
                bigLabel.setTextFill(RED);
            }
        forDelete.clear();

    }
}
