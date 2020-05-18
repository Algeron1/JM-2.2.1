package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public User getUserBySeries(int series) {
        Query query = sessionFactory.getCurrentSession().createQuery("from User us where us.car.series=?1");
        query.setParameter(1, series);
        return (User) query.getResultList().get(0);
    }

    @Override
    @SuppressWarnings("unchecked")
    public User getUserByCarModel(String model) {
        Query query = sessionFactory.getCurrentSession().createQuery("from User u where u.car.model=?1");
        query.setParameter(1, model);
        List<User> users = query.getResultList();
        return users.get(0);
    }
}
