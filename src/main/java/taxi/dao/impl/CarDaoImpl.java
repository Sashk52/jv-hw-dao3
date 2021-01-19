package taxi.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import taxi.dao.CarDao;
import taxi.db.Storage;
import taxi.model.Car;

public class CarDaoImpl implements CarDao {

    @Override
    public Car create(Car car) {
        Storage.addCar(car);
        return car;
    }

    @Override
    public Optional<Car> get(Long id) {

        return Storage.cars.stream()
                .filter(x -> x.getId()
                        .equals(id)).findFirst();
    }

    @Override
    public List<Car> getAll() {
        return Storage.cars;
    }

    @Override
    public Car update(Car car) {
        for (int i = 0; i < Storage.cars.size(); i++) {
            if (car.getId().equals(Storage.cars.get(i).getId())) {
                Storage.cars.set(i, car);
                break;
            }
        }
        return car;
    }

    @Override
    public boolean delete(Long id) {
        return Storage.cars.removeIf(m -> m.getId().equals(id));
    }

    @Override
    public List<Car> getAllByDriver(Long driverId) {
        List<Car> cars = new ArrayList<>();
        for (Car car : Storage.cars) {
            for (int i = 0; i < car.getDrivers().size(); i++) {
                if (car.getDrivers().get(i).getId().equals(driverId)) {
                    cars.add(car);
                }
            }
        }
        return cars;
    }
}
