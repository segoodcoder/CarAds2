import cars.app.loading.MainWindow;
import cars.app.logic.Car;
import cars.app.logic.MarketPlace;
import org.junit.Assert;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MarketPlaceTest {

    @Test
    public void getUserToAdvert() {
        Map<String, ArrayList<Integer>> m = new HashMap<>();
        MarketPlace.setUserToAdvert(m);
        Assert.assertEquals(m, MarketPlace.getUserToAdvert());
    }

    @Test
    public void updateUserToAdvert() {
        String username = "user1";
        MainWindow.setUSER(username);
        Car car = new Car("Volvo", "V90", 2020, 2231L, 23123, 4000000,
                new File("C:\\img\\90.jpg"), "good car");
        ArrayList<Integer> l = new ArrayList<>();
        l.add(car.hashCode());
        Map<String, ArrayList<Integer>> m = new HashMap<>();
        m.put(username, l);
        MarketPlace.updateUserToAdvert(car);
        Assert.assertEquals(m, MarketPlace.getUserToAdvert());
    }

    @Test
    public void loadUserToAdvert() throws IOException {
        String username = "user1";
        MainWindow.setUSER(username);
        MarketPlace.setPathToCacheAds("testing/forTest.txt");
        ArrayList<Integer> i = new ArrayList<>();
        i.add(982138231);
        i.add(21313313);
        i.add(-213312313);
        Map<String, ArrayList<Integer>> m = new HashMap<>();
        m.put(username, i);
        MarketPlace.loadUserToAdvert();
        Assert.assertEquals(m, MarketPlace.getUserToAdvert());
    }

    @Test
    public void getListOfCars() {
        Map<Integer, Car> m = new HashMap<>();
        Car car = new Car("Volvo", "V90", 2020, 2231L, 23123, 4000000,
                new File("C:\\img\\90.jpg"), "good car");
        m.put(car.hashCode(), car);
        MarketPlace.setListOfCars(m);
        Assert.assertEquals(MarketPlace.getListOfCars(), m);
    }

    @Test
    public void loadListOfCars() throws IOException {
        Map<Integer, Car> m = new HashMap<>();
        Car car = new Car("Volvo", "V90", 2020, 2231L, 23123, 4000000,
                new File("C:\\img\\90.jpg"), "good car");
        m.put(car.hashCode(), car);
        MarketPlace.setPathToCash("testing/forTest2.txt");
        MarketPlace.loadListOfCars();
        Assert.assertEquals(MarketPlace.getListOfCars(), m);
    }

    @Test
    public void saveCarsAndUsers() throws IOException {
        String path = "testing/forTest3.txt";
        String pathExpected = "testing/forTest3Expected.txt";
        MarketPlace.setPathToCacheAds(path);
        String u = "user333";
        MainWindow.setUSER(u);
        Map<String, ArrayList<Integer>> m = new HashMap<>();
        ArrayList<Integer> r = new ArrayList<>();
        r.add(21332421);
        r.add(34242343);
        m.put(u, r);
        MarketPlace.setUserToAdvert(m);
        MarketPlace.saveCarsAndUsers();
        Assert.assertTrue(fileEquals(path, pathExpected));
    }

    @Test
    public void addNewAd() {
        Map<Integer, Car> m = new HashMap<>();
        Car car = new Car("Volvo", "V90", 2020, 2231L, 23123, 4000000,
                new File("C:\\img\\90.jpg"), "good car");
        m.put(car.hashCode(), car);
        MarketPlace.addNewAd(car);
        Assert.assertEquals(MarketPlace.getListOfCars(), m);
    }

    public boolean fileEquals(String name1, String name2) throws IOException {
        FileReader fr1 = new FileReader(name1);
        BufferedReader br1 = new BufferedReader(fr1);
        ArrayList<String> l1 = new ArrayList<>();
        FileReader fr2 = new FileReader(name2);
        BufferedReader br2 = new BufferedReader(fr2);
        ArrayList<String> l2 = new ArrayList<>();
        String s1;
        String s2;
        s1 = br1.readLine();
        while (s1 != null) {
            l1.add(s1);
            s1 = br1.readLine();
        }
        s2 = br2.readLine();
        while (s2 != null) {
            l2.add(s2);
            s2 = br2.readLine();
        }
        return l1.equals(l2);
    }
}