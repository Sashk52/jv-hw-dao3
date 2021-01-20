package taxi.dao;

import java.util.List;
import java.util.Optional;

public interface GenericDao<T, V> {
    T create(T t);

    Optional<T> get(V id);

    List<T> getAll();

    T update(T t);

    boolean delete(V id);
}
