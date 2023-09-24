package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {


    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Transactional(readOnly = true)
    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    @Transactional
    public User getOwner(String model, int series) {
        TypedQuery<Car> query = sessionFactory.getCurrentSession().createQuery("from Car where model =:model and series=:series");
        query.setParameter("model", model);
        query.setParameter("series", series);
        Car findCar = query.getSingleResult();

        TypedQuery<User> userTypedQuery = sessionFactory.getCurrentSession().createQuery("from User where id =:car_id");
        userTypedQuery.setParameter("car_id", findCar.getId());

        return userTypedQuery.getSingleResult();
    }


}
