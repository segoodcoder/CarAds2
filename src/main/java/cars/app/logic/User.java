package cars.app.logic;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;

public class User {

    private String login;
    private String password;
    private ArrayList<Integer> myCars;

    public void setPassword(String password) {
        this.password = password;
    }
    public void setLogin(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }
    public String getPassword() {
        return password;
    }

    public void myNewCarAd(Integer id) {
        myCars.add(id);
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }
}
