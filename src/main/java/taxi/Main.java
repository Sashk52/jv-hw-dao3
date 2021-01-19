package taxi;

import java.util.List;
import taxi.lib.Injector;
import taxi.model.Car;
import taxi.model.Driver;
import taxi.model.Manufacturer;
import taxi.service.CarService;
import taxi.service.DriverService;
import taxi.service.ManufacturerService;

public class Main {
    private static final Injector injector = Injector.getInstance("taxi");

    public static void main(String[] args) {
        ManufacturerService manufacturerService = (ManufacturerService) injector
                .getInstance(ManufacturerService.class);

        System.out.println("Test manufacturer:");

        Manufacturer manufacturer1 = new Manufacturer("Opel", "Germany");
        Manufacturer manufacturer2 = new Manufacturer("Renault", "France");

        manufacturerService.create(manufacturer1);
        manufacturerService.create(manufacturer2);

        List<Manufacturer> manufacturers = manufacturerService.getAll();
        System.out.println(manufacturers);

        manufacturerService.update(manufacturer1);
        manufacturerService.update(manufacturer2);

        manufacturers = manufacturerService.getAll();
        System.out.println(manufacturers);

        System.out.println(manufacturerService.get(1L));
        System.out.println(manufacturerService.get(2L));

        manufacturerService.delete(2L);

        manufacturers = manufacturerService.getAll();
        System.out.println(manufacturers);

        System.out.println("Test driver: ");
        DriverService driverService = (DriverService) injector
                .getInstance(DriverService.class);

        Driver driver1 = new Driver("Jo", "1111");
        Driver driver2 = new Driver("Sam", "2222");

        driverService.create(driver1);
        driverService.create(driver2);
        List<Driver> allDrivers = driverService.getAll();
        System.out.println(allDrivers);

        System.out.println(driverService.get(1L));
        System.out.println(driverService.get(2L));

        driver1.setLicenceNumber("3333");
        driverService.update(driver1);

        allDrivers = driverService.getAll();
        System.out.println(allDrivers);

        driverService.delete(1L);

        allDrivers = driverService.getAll();
        System.out.println(allDrivers);

        System.out.println("Test car: ");

        CarService carService = (CarService) injector
                .getInstance(CarService.class);

        Car car1 = new Car("Astra", manufacturer1);
        Car car2 = new Car("Insignia", manufacturer1);

        carService.create(car1);
        carService.create(car2);
        car1.setDrivers(allDrivers);
        System.out.println(carService.get(1L));
        System.out.println(carService.get(2L));

        car1.setModel("Vectra");
        carService.update(car1);

        List<Car> cars = carService.getAll();
        System.out.println(cars);

        carService.delete(1L);

        cars = carService.getAll();
        System.out.println(cars);

    }
}
