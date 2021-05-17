import cars.app.logic.MarketPlace;
import org.junit.Assert;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class MarketPlaceTest {

    @org.junit.Test
    public void getInstance() {
    }

    @org.junit.Test
    public void getUserToAdvert() {
    }

    @org.junit.Test
    public void updateUserToAdvert() {
    }

    @org.junit.Test
    public void loadUserToAdvert() throws IOException {
        Map<String, ArrayList<Integer>> testMap = new HashMap<>();
//        testMap.put("");

        MarketPlace.loadUserToAdvert();
        Assert.assertEquals(testMap, MarketPlace.getUserToAdvert());
    }

    @org.junit.Test
    public void getListOfCars() {
    }

    @org.junit.Test
    public void loadListOfCars() {
    }

    @org.junit.Test
    public void save() {
    }

    @org.junit.Test
    public void addNewAd() {
    }
}