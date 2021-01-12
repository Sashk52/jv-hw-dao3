package db;

import model.Car;
import model.Manufacturer;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    public static final List<Manufacturer> manufacturers =new ArrayList<>();
    public static final List<Car> carsList=new ArrayList<>();

    private static long manufacturerId = 0;
    private static long carsId = 0;

    public static void addDCar(Car car){
        carsId++;
        car.setId(carsId);
        carsList.add(car);
    }
    public static void addManufacturer(Manufacturer manufacturer){
        manufacturerId++;
        manufacturer.setId(manufacturerId);
        manufacturers.add(manufacturer);
    }
}
