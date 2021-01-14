package taxi.dao.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;
import taxi.dao.ManufacturerDao;
import taxi.lib.Dao;
import taxi.model.Manufacturer;
import taxi.util.ConnectionUtil;

@Dao
public class ManufacturerDaoJdbcImpl implements ManufacturerDao {
    @Override
    public Manufacturer create(Manufacturer manufacturer) {
        Connection connection = ConnectionUtil.getConnection();
        String sqlQuery = "INSERT INTO manufacturer (manufacturer_name, manufacturer_country)"
                + "VALUES (?,?);";

        return null;
    }

    @Override
    public Optional<Manufacturer> get(Long id) {
        Connection connection = ConnectionUtil.getConnection();
        return Optional.empty();
    }

    @Override
    public List<Manufacturer> getAll() {
        Connection connection = ConnectionUtil.getConnection();
        return null;
    }

    @Override
    public Manufacturer update(Manufacturer manufacturer) {
        Connection connection = ConnectionUtil.getConnection();
        return null;
    }

    @Override
    public boolean delete(Long id) {
        Connection connection = ConnectionUtil.getConnection();
        return false;
    }
}
