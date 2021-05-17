package cars.app.logic;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BaseOfUsers {

    public static Map <String, String> getListOfUsers() {
        return listOfUsers;
    }

    public static void loadListOfUsers() throws IOException {

        FileReader r = new FileReader("src/main/java/cars/app/cache/cacheUsers.txt");
        BufferedReader br = new BufferedReader(r);
        String line = br.readLine();
        try {
            while (!line.equals("")) {
                String[] m = line.split("//");
                getListOfUsers().put(m[0], m[1]);
                line = br.readLine();
            }
        } catch (NullPointerException ignored) {

        }
    }

    private static final Map <String, String> listOfUsers = new HashMap<>();
    private static BaseOfUsers INSTANCE;

    public static BaseOfUsers getInstance() {
        if ( INSTANCE == null) {
            INSTANCE = new BaseOfUsers();
        }
        return INSTANCE;
    }

    public static void addNewUser (User user) {
        listOfUsers.put(user.getLogin(), user.getPassword());
    }
}
