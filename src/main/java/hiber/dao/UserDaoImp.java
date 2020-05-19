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
    public User getUserByCarModelAndSeries(String carmodel, int series) {
        Query query = sessionFactory.getCurrentSession().createQuery("from User us where us.car.series=?1 and us.car.model=?2");
        query.setParameter(1, series);
        query.setParameter(2, carmodel);
        return (User) query.getSingleResult();
    }
}
