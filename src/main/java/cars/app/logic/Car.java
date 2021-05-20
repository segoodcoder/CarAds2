package cars.app.logic;

import java.io.File;
import java.util.Objects;

public class Car {

    private final String brand;
    private final String model;
    private final Integer year;
    private final Long phone;
    private final Integer mileage;
    private final Integer price;
    private final File image;
    private final String briefInfo;

    @Override
    public int hashCode() {
        int s = Objects.hash(brand, model, mileage);
        if (s < 0) return -s;
        else return s;
    }


    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (o == null) return false;
        else {
            if (o.getClass() != this.getClass()) {
                return false;
            }
            final Car car = (Car) o;
            return this.brand.equals(car.brand) && this.model.equals(car.model) &&
            this.mileage.equals(car.mileage) && this.phone.equals(car.phone) &&
            this.price.equals(car.price);
        }
    }

    @Override
    public String toString() {
        return hashCode() + "//" + brand + "//" + model + "//" + year + "//" + phone + "//" + mileage + "//" + price + "//" + image.getPath() + "//" + briefInfo;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public Integer getYear() {
        return year;
    }

    public Long getPhone() {
        return phone;
    }

    public Integer getMileage() {
        return mileage;
    }

    public Integer getPrice(){
        return price;
    }

    public File getImage() {
        return image;
    }

    public String getBriefInfo() {
        return briefInfo;
    }
    public Car(String brand, String model, int year, Long phone, int mileage, int price, File image, String briefInfo) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.phone = phone;
        this.mileage = mileage;
        this.price = price;
        this.image = image;
        this.briefInfo = briefInfo;
    }

}
