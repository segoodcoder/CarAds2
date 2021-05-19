package cars.app.logic;

import java.io.File;

public class Car {

    private final String brand;
    private final String model;
    private final String year;
    private final String phone;
    private final String mileage;
    private final String price;
    private final File image;
    private final String briefInfo;

    @Override
    public int hashCode() {
        int result = 31;
        result *= brand.hashCode();
        result *= model.hashCode();
        result *= mileage.hashCode();
        return result;
    }


    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        else {
            if (!(o instanceof Car)) {
                return false;
            }
            Car car = (Car) o;
            return car.brand.equals(((Car) o).brand) && car.model.equals(((Car) o).model) &&
            car.mileage.equals(((Car) o).mileage) && car.phone.equals(((Car) o).phone) &&
            car.price.equals(((Car) o).price);
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

    public String getYear() {
        return year;
    }

    public String getPhone() {
        return phone;
    }

    public String getMileage() {
        return mileage;
    }

    public String getPrice(){
        return price;
    }

    public File getImage() {
        return image;
    }

    public String getBriefInfo() {
        return briefInfo;
    }
    public Car(String brand, String model, String year, String phone, String mileage, String price, File image, String briefInfo) {
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
