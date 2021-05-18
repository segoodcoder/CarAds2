package cars.app.logic;

import java.io.*;
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
                Map<String, String> tmp = BaseOfUsers.getListOfUsers();

                tmp.put(m[0], m[1]);
                BaseOfUsers.setListOfUsers(tmp);

                line = br.readLine();
            }
        } catch (NullPointerException ignored) {

        }
    }

    public static void listOfUsersToMemory() throws IOException {
        FileWriter fw = new FileWriter("src/main/java/cars/app/cache/cacheUsers.txt", false);
        Map<String, String> temp = BaseOfUsers.getListOfUsers();
        for (Map.Entry<String, String> user : temp.entrySet()) {
            fw.write(user.getKey() + "//" + user.getValue());
            fw.write("\n");
        }
        fw.close();
    }

    public static void setListOfUsers(Map<String, String> listOfUsers) {
        BaseOfUsers.listOfUsers = listOfUsers;
    }

    private static Map <String, String> listOfUsers = new HashMap<>();
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
