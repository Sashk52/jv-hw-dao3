package taxi.service;

import java.util.ArrayList;
import java.util.List;
import taxi.dao.CarDao;
import taxi.lib.Inject;
import taxi.lib.Service;
import taxi.model.Car;
import taxi.model.Driver;

@Service
public class CarServiceImpl implements CarService {

    @Inject
    private CarDao carDao;

    @Override
    public Car create(Car car) {
        return carDao.create(car);
    }

    @Override
    public Car get(Long id) {
        return carDao.get(id).get();
    }

    @Override
    public List<Car> getAll() {
        return carDao.getAll();
    }

    @Override
    public Car update(Car car) {
        return carDao.update(car);
    }

    @Override
    public boolean delete(Long id) {
        return carDao.delete(id);
    }

    @Override
    public void addDriverToCar(Driver driver, Car car) {
        List<Driver> newDriverList = car.getDrivers();
        newDriverList.add(driver);
        car.setDrivers(newDriverList);
        carDao.update(car);
    }

    @Override
    public void removeDriverFromCar(Driver driver, Car car) {
        car.getDrivers().remove(driver);
        carDao.update(car);
    }

    @Override
    public List<Car> getAllByDriver(Long driverId) {
        List<Car> cars = new ArrayList<>();
        for (Car car : carDao.getAll()) {
            for (int i = 0; i < car.getDrivers().size(); i++) {
                if (car.getDrivers().get(i).getId().equals(driverId)) {
                    cars.add(car);
                }
            }
        }
        return cars;
    }
}
