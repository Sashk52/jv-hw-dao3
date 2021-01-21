package taxi.service;

import java.util.List;

public interface GenericService<T, V> {
    T create(T t);

    T get(V id);

    List<T> getAll();

    T update(T t);

    boolean delete(V id);
}
