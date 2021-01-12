package service;

import dao.ManufacturerDao;
import db.Storage;
import lib.Inject;
import lib.Service;
import model.Manufacturer;
import java.util.List;
import java.util.Optional;

@Service
public class ManufacturerServiceImpl implements ManufacturerService{

    @Inject
    private ManufacturerDao manufacturerDao;

    @Override
    public Manufacturer create(Manufacturer manufacturer) {
        return manufacturerDao.create(manufacturer);
    }

    @Override
    public Manufacturer get(Long id) {
        return Optional.of(Storage.manufacturers.get(Math.toIntExact(id))).get();
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
        Manufacturer manufacturer = Optional.of(Storage.manufacturers.get(Math.toIntExact(id))).get();
        if (Storage.manufacturers.remove(manufacturer)){
            return true;
        }
        return false;
    }
}
