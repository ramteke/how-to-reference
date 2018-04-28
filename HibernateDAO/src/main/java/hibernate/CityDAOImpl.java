package hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;



public class CityDAOImpl implements CityDAO<City, String> {

    final private Session session;
    final private Transaction transaction;

    public CityDAOImpl() {
        session = getSessionFactory().getCurrentSession();
        transaction = session.getTransaction();
        transaction.begin();
    }


    private static SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration().configure();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
            .applySettings(configuration.getProperties());
        SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
        return sessionFactory;
    }

    public void closeSession() {
        if (!transaction.wasCommitted()) {
            transaction.commit();
        }
        if ( session.isConnected()) {
            session.close();
        }
    }

    public void persist(City entity) {
        session.save(entity);
    }

    public void update(City entity) {
        session.update(entity);
    }

    public City findByCityName(String name) {
        City city = (City) session.get(City.class, name);
        return city;
    }

    public void delete(City entity) {
        session.delete(entity);
    }


    public List<City> findAll() {
        List<City> cities = (List<City>) session.createQuery("from hibernate.City").list();
        return cities;
    }


}