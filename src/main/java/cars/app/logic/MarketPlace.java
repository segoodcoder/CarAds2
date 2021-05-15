package cars.app.logic;

import javafx.scene.control.Label;
import cars.app.loading.ControllerAdd;
import cars.app.loading.MainWindow;

import java.io.*;
import java.util.LinkedList;

import static javafx.scene.paint.Color.RED;

public class MarketPlace {

    private static final LinkedList<Car> listOfCars = new LinkedList<Car>();
    private static MarketPlace INSTANCE;

    public static MarketPlace getInstance() {
        if ( INSTANCE == null) {
            INSTANCE = new MarketPlace();
        }
        return INSTANCE;
    }

    public static LinkedList<Car> getListOfCars() {
        return listOfCars;
    }

    public static LinkedList<Car> loadListOfCars() throws IOException {
        try {
            LinkedList<Car> temp = new LinkedList<Car>();
            Reader r = new FileReader("src//main//java//cars//app//cache//cache.txt");
            BufferedReader br = new BufferedReader(r);
            String s = br.readLine();
            while (!s.equals("")) {
                String[] m = s.split(" ");
                Car car = new Car(m[0], m[1], m[2], m[3], m[4], m[5], new File(m[6]), m[7]);
                temp.add(car);
                s = br.readLine();
                if (s == null) break;
            }
            return temp;
        } catch (NullPointerException n) {
            Label noAds = new Label("Объявлений пока нет!");
            noAds.setTextFill(RED);
            MainWindow.l = noAds ;
            return null;
        }
    }

    public static void save() throws IOException {
        FileWriter fw = new FileWriter("src//main//java//cars//app//cache//cache.txt", true);
        for (Car car: listOfCars) {
            fw.write(car.toString());
            fw.write('\n');
            fw.flush();
        }
    }

    public static void addNewAd (Car car) {
        listOfCars.add(car);
    }

    private MarketPlace() { }

}
