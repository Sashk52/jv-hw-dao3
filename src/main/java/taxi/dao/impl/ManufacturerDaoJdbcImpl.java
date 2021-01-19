package taxi.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
        String sqlQuery = "INSERT INTO manufacturer (name, country)"
                + "VALUES (?,?);";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement preparedStatement = connection
                        .prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, manufacturer.getName());
            preparedStatement.setString(2, manufacturer.getCountry());
            preparedStatement.executeUpdate();
            ResultSet resultset = preparedStatement.getGeneratedKeys();
            while (resultset.next()) {
                manufacturer.setId(resultset.getObject(1, Long.class));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Can't insert manufacturer " + manufacturer, e);
        }
        return manufacturer;
    }

    @Override
    public Optional<Manufacturer> get(Long id) {
        String sqlQuery = "SELECT * FROM manufacturer WHERE manufacturer_id =?;";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);) {
            preparedStatement.setLong(1, id);
            ResultSet resultset = preparedStatement.executeQuery();
            Manufacturer manufacturer = null;
            if (resultset.next()) {
                manufacturer = createManufacturer(resultset);
            }
            return Optional.ofNullable(manufacturer);
        } catch (SQLException e) {
            throw new RuntimeException("Can't get manufacturer by id " + id, e);
        }
    }

    @Override
    public List<Manufacturer> getAll() {
        String query = "SELECT * FROM manufacturer WHERE deleted = false;";
        List<Manufacturer> manufacturers = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultset = preparedStatement.executeQuery();
            while (resultset.next()) {
                manufacturers.add(createManufacturer(resultset));
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Can't getAll manufacturers ", ex);
        }
        return manufacturers;
    }

    @Override
    public Manufacturer update(Manufacturer manufacturer) {
        String query = "UPDATE manufacturer SET name=?, country=?"
                + " WHERE manufacturer_id =?;";
        try (Connection connection = ConnectionUtil.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, manufacturer.getName());
            preparedStatement.setString(2, manufacturer.getCountry());
            preparedStatement.setLong(3, manufacturer.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException("Can't update manufacturer " + manufacturer, ex);
        }
        return manufacturer;
    }

    @Override
    public boolean delete(Long id) {
        String sqlQuery = "UPDATE manufacturer"
                + " SET deleted = TRUE"
                + " WHERE manufacturer_id = ?;";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            preparedStatement.setLong(1, id);
            int updatesMade = preparedStatement.executeUpdate();
            return updatesMade > 0;
        } catch (SQLException ex) {
            throw new RuntimeException("Can't delete manufacturer by id " + id, ex);
        }
    }

    private Manufacturer createManufacturer(ResultSet resultset) throws SQLException {
        Long id = resultset.getObject("manufacturer_id", Long.class);
        String name = resultset.getObject("name", String.class);
        String country = resultset.getObject("country", String.class);
        Manufacturer manufacturer = new Manufacturer(name, country);
        manufacturer.setId(id);
        return manufacturer;
    }
}
