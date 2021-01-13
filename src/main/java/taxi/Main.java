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

        Manufacturer manufacturer1 = new Manufacturer("Opel", "Germany");
        Manufacturer manufacturer2 = new Manufacturer("Renault", "France");
        Manufacturer manufacturer3 = new Manufacturer("GeneralMotors", "USA");

        manufacturerService.create(manufacturer1);
        manufacturerService.create(manufacturer2);
        manufacturerService.create(manufacturer3);

        CarService carService = (CarService) injector
                .getInstance(CarService.class);

        Car car1 = new Car("Golf", manufacturer1);
        Car car2 = new Car("407", manufacturer2);
        Car car3 = new Car("Lacetti", manufacturer3);

        carService.create(car1);
        carService.create(car2);
        carService.create(car3);

        DriverService driverService = (DriverService) injector
                .getInstance(DriverService.class);

        Driver driver1 = new Driver("Jo", "1111");
        Driver driver2 = new Driver("Sam", "2222");
        Driver driver3 = new Driver("Jessi", "3333");

        driverService.create(driver1);
        driverService.create(driver2);
        driverService.create(driver3);

        List<Manufacturer> manufacturers = manufacturerService.getAll();
        System.out.println(manufacturers);

        manufacturer1.setName("Volkswagen");
        manufacturer2.setName("Peugeot");
        manufacturer3.setName("Chevrolet");

        manufacturerService.update(manufacturer1);
        manufacturerService.update(manufacturer2);
        manufacturerService.update(manufacturer3);

        manufacturers = manufacturerService.getAll();
        System.out.println(manufacturers);

        System.out.println(manufacturerService.get(0L));
        System.out.println(manufacturerService.get(1L));
        System.out.println(manufacturerService.get(2L));

        manufacturerService.delete(0L);
        manufacturerService.delete(1L);
        manufacturerService.delete(2L);

        manufacturers = manufacturerService.getAll();
        System.out.println(manufacturers);

        List<Car> cars = carService.getAll();
        System.out.println(cars);

        car1.setModel("Jetta");
        car2.setModel("507");
        car3.setModel("Aveo");

        carService.update(car1);
        carService.update(car2);
        carService.update(car3);

        cars = carService.getAll();
        System.out.println(cars);

        System.out.println(carService.get(0L));
        System.out.println(carService.get(1L));
        System.out.println(carService.get(2L));

        carService.addDriverToCar(driver1, car1);
        carService.addDriverToCar(driver2, car2);
        carService.addDriverToCar(driver3, car3);

        cars = carService.getAll();
        System.out.println(cars);

        carService.getAllByDriver(driver1.getId());
        carService.getAllByDriver(driver2.getId());
        carService.getAllByDriver(driver3.getId());

        cars = carService.getAll();
        System.out.println(cars);

        carService.removeDriverFromCar(driver1, car1);
        carService.removeDriverFromCar(driver2, car2);
        carService.removeDriverFromCar(driver3, car3);

        carService.delete(0L);
        carService.delete(1L);
        carService.delete(2L);

        cars = carService.getAll();
        System.out.println(cars);

        List<Driver> drivers = driverService.getAll();
        System.out.println(drivers);

        System.out.println(driverService.get(0L));
        System.out.println(driverService.get(1L));
        System.out.println(driverService.get(2L));

        driver1.setName("Jim");
        driver2.setName("Monica");
        driver3.setName("Olivia");

        driverService.update(driver1);
        driverService.update(driver2);
        driverService.update(driver3);

        drivers = driverService.getAll();
        System.out.println(drivers);

        driverService.delete(0L);
        driverService.delete(1L);
        driverService.delete(2L);

        drivers = driverService.getAll();
        System.out.println(drivers);
    }
}
