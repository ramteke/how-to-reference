package hibernate;

import java.io.Serializable;
import java.util.List;

public interface CityDAO<T, Id extends Serializable> {

    public void persist(T entity);

    public void update(T entity);

    public T findByCityName(Id id);

    public void delete(T entity);

    public List<T> findAll();

    public void closeSession();

}