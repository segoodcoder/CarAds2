package cars.app.logic;

import javafx.scene.control.Label;
import cars.app.loading.ControllerAdd;
import cars.app.loading.MainWindow;
import javafx.util.converter.IntegerStringConverter;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import static cars.app.loading.MainWindow.USER;
import static javafx.scene.paint.Color.RED;

public class MarketPlace {


    public static void setListOfCars(Map<Integer, Car> listOfCars) {
        MarketPlace.listOfCars = listOfCars;
    }

    private static Map<Integer, Car> listOfCars = new HashMap<>();


    private static MarketPlace INSTANCE;

    public static MarketPlace getInstance() {
        if ( INSTANCE == null) {
            INSTANCE = new MarketPlace();
        }
        return INSTANCE;
    }

    public static Map<String, ArrayList<Integer>> getUserToAdvert() {
        return userToAdvert;
    }

    public static void setUserToAdvert(Map<String, ArrayList<Integer>> userToAdvert) {
        MarketPlace.userToAdvert = userToAdvert;
    }

    public static Map<String, ArrayList<Integer>> userToAdvert = new HashMap<>();

    public static void updateUserToAdvert(Car car) {
        if (userToAdvert.containsKey(USER)) {
            ArrayList<Integer> ll = userToAdvert.get(USER);
            ll.add(car.hashCode());
            userToAdvert.put(MainWindow.getUSER(), ll);
        }
        else {
            ArrayList<Integer> ll1 = new ArrayList<>();
            ll1.add(car.hashCode());
            userToAdvert.put(USER, ll1);
        }
    }

    public static void setPathToCacheAds(String pathToCacheAds) {
        MarketPlace.pathToCacheAds = pathToCacheAds;
    }

    private static String pathToCacheAds = "src//main//java//cars//app//cache//cacheAds.txt";

    public static void setPathToCash(String pathToCash) {
        MarketPlace.pathToCash = pathToCash;
    }

    private static String pathToCash = "src//main//java//cars//app//cache//cache.txt";

    public static void loadUserToAdvert() throws FileNotFoundException {
        FileReader r = new FileReader(pathToCacheAds);
        BufferedReader br = new BufferedReader(r);
        try {
            try {
                String line = br.readLine();
                while (!line.equals("")) {
                    String[] m = line.split("//");
                    String[] l = m[1].split(" ");
                    ArrayList<Integer> list = new ArrayList<>();
                    for (String elem : l) {
                        list.add(Integer.valueOf(elem));
                    }

                    Map<String, ArrayList<Integer>> m1 = MarketPlace.getUserToAdvert();
                    m1.put(m[0], list);
                    MarketPlace.setUserToAdvert(m1);

                    line = br.readLine();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (NullPointerException ignored) {

        }
    }

    public static Map<Integer, Car> getListOfCars() {
        return listOfCars;
    }

    public static void loadListOfCars() throws IOException {
        try {
            Map<Integer, Car> temp = new HashMap<>();
            Reader r = new FileReader(pathToCash);
            BufferedReader br = new BufferedReader(r);
            String s = br.readLine();
            while (!s.equals("")) {
                String[] m = s.split(">>");
                Integer hash = Integer.valueOf(m[0]);
                String[] l = m[1].split("//");
                Car car = new Car(l[1], l[2], l[3], l[4], l[5], l[6], new File(l[7]), l[8]);
                temp.put(hash, car);
                s = br.readLine();
                if (s == null) break;
            }
            MarketPlace.setListOfCars(temp);
        } catch (NullPointerException ignored) {

        }
    }


    public static void saveCarsFalse() throws IOException {
        FileWriter fw = new FileWriter(pathToCash, false);
        for (Map.Entry<Integer, Car> entry : listOfCars.entrySet()) {
            fw.write(entry.getKey().toString());
            fw.write(">>");
            fw.write(entry.getValue().toString());
            fw.write('\n');
            fw.flush();
        }
        fw.close();

    }

    public static void addNewAd (Car car) {
        listOfCars.put(car.hashCode(), car);
    }

    private MarketPlace() { }

    public static void saveCarsAndUsers() throws IOException {
        FileWriter fw2 = new FileWriter(pathToCacheAds, false);
        for (Map.Entry<String, ArrayList<Integer>> entry : MarketPlace.getUserToAdvert().entrySet()) {
            fw2.write(entry.getKey());
            fw2.write("//");
            ArrayList<Integer> list = entry.getValue();
            for (int i = 0; i < list.size(); i ++) {
                if (i != list.size() - 1) {
                    fw2.write(list.get(i).toString());
                    fw2.write(" ");
                }
                else {
                    fw2.write(list.get(i).toString());
                }
            }
            fw2.write("\n");
            fw2.flush();
        }
        fw2.close();
    }
}
