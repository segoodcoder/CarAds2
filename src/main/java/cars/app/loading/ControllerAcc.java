package cars.app.loading;

import cars.app.logic.BaseOfUsers;
import cars.app.logic.User;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;

import static cars.app.loading.MainWindow.USER;
import static javafx.scene.paint.Color.GREEN;
import static javafx.scene.paint.Color.RED;

import cars.app.logic.MarketPlace;
import javafx.stage.WindowEvent;

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

        p.show();
    }

    public void addNewUser() throws IOException {

        String newLogin = login.getText();
        String newPassword = password.getText();
        User newUser = new User(newLogin, newPassword);

        FileReader fr = new FileReader("src/main/java/cars/app/cache/cacheUsers.txt");
        FileWriter fw = new FileWriter("src/main/java/cars/app/cache/cacheUsers.txt", true);
        BufferedReader br = new BufferedReader(fr);
        String line = br.readLine();

        try {
            while (!line.equals("")) {
                if (!line.equals(newLogin + "//" + newPassword)) {
                    line = br.readLine();
                } else {
                    login.clear();
                    password.clear();
                    lc.setText("Такой пользователь уже существует!");
                    lc.setTextFill(RED);
                    lc.setWrapText(true);
                    break;
                }

            }
        } catch (NullPointerException ignored) {

        }
        BaseOfUsers.addNewUser(newUser);
        lc.setText("Вы успешно зарегистрированы!");
        lc.setTextFill(GREEN);
        br.close();
        fr.close();

        fw.write(newLogin + "//" + newPassword);
        fw.write("\n");
        fw.close();
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

        String user = MainWindow.getUSER();
        Integer id = Integer.valueOf(forDelete.getText());
            if (MarketPlace.getUserToAdvert().get(user).contains(id)) {
                MarketPlace.getUserToAdvert().get(user).remove(id);
                MarketPlace.getListOfCars().remove(id);
            }
            else {
                bigLabel.setText("Похоже, это не ваше объявление!");
                bigLabel.setTextFill(RED);
            }
        forDelete.clear();
        MarketPlace.save();
    }
}
