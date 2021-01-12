package dao.impl;

import dao.ManufacturerDao;
import db.Storage;
import java.util.List;
import java.util.Optional;
import lib.Dao;
import model.Manufacturer;

@Dao
public class ManufacturerDaoImpl implements ManufacturerDao {
    @Override
    public Manufacturer create(Manufacturer manufacturer) {
        Storage.manufacturers.add(manufacturer);
        return manufacturer;
    }

    @Override
    public Optional<Manufacturer> get(Long id) {
        return Optional.of(Storage.manufacturers.get(Math.toIntExact(id)));
    }

    @Override
    public List<Manufacturer> getAll() {
        return Storage.manufacturers;
    }

    @Override
    public Manufacturer update(Manufacturer manufacturer) {
        Storage.manufacturers.add(manufacturer);
        return manufacturer;
    }

    @Override
    public boolean delete(Long id) {
        Manufacturer manufacturer = Optional.of(Storage.manufacturers
                .get(Math.toIntExact(id))).get();
        if (Storage.manufacturers.remove(manufacturer)) {
            return true;
        }
        return false;
    }
}
