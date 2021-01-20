package taxi.service;

import taxi.model.Car;
import taxi.model.Driver;

public interface CarService extends GenericService<Car, Long> {

    void addDriverToCar(Driver driver, Car car);

    void removeDriverFromCar(Driver driver, Car car);
}
