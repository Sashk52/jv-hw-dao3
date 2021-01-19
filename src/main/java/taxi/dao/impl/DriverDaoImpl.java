package taxi.dao.impl;

import java.util.List;
import java.util.Optional;
import taxi.dao.DriverDao;
import taxi.db.Storage;
import taxi.model.Driver;

public class DriverDaoImpl implements DriverDao {

    @Override
    public Driver create(Driver driver) {
        Storage.addDriver(driver);
        return driver;
    }

    @Override
    public Optional<Driver> get(Long id) {
        return Storage.drivers.stream().filter(x -> x.getId().equals(id)).findFirst();
    }

    @Override
    public List<Driver> getAll() {
        return Storage.drivers;
    }

    @Override
    public Driver update(Driver driver) {
        for (int i = 0; i < Storage.drivers.size(); i++) {
            if (driver.getId().equals(Storage.drivers.get(i).getId())) {
                Storage.drivers.set(i, driver);
                break;
            }
        }
        return driver;
    }

    @Override
    public boolean delete(Long id) {
        return Storage.drivers.removeIf(m -> m.getId().equals(id));
    }
}
