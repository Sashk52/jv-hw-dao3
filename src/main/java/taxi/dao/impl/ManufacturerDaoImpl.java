package taxi.dao.impl;

import java.util.List;
import java.util.Optional;
import taxi.dao.ManufacturerDao;
import taxi.db.Storage;
import taxi.lib.Dao;
import taxi.model.Manufacturer;

@Dao
public class ManufacturerDaoImpl implements ManufacturerDao {

    @Override
    public Manufacturer create(Manufacturer manufacturer) {
        Storage.addManufacturer(manufacturer);
        return manufacturer;
    }

    @Override
    public Optional<Manufacturer> get(Long id) {
        return Storage.manufacturers.stream().filter(x -> x.getId().equals(id)).findFirst();
    }

    @Override
    public List<Manufacturer> getAll() {
        return Storage.manufacturers;
    }

    @Override
    public Manufacturer update(Manufacturer manufacturer) {
        for (int i = 0; i < Storage.manufacturers.size(); i++) {
            if (manufacturer.getId().equals(Storage.manufacturers.get(i).getId())) {
                Storage.manufacturers.set(i, manufacturer);
            }
        }
        return manufacturer;
    }

    @Override
    public boolean delete(Long id) {
        List<Manufacturer> manufacturers = Storage.manufacturers;
        for (int i = 0; i < manufacturers.size(); i++) {
            if (manufacturers.get(i).getId().equals(id)) {
                Storage.manufacturers.remove(i);
                return true;
            }
        }
        return false;
    }
}
