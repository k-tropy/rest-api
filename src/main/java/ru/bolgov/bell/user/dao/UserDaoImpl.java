package ru.bolgov.bell.user.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bolgov.bell.user.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private final EntityManager em;

    @Autowired
    public UserDaoImpl(EntityManager em) {
        this.em = em;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<User> loadAllUsers() {
        String queryString = "SELECT u FROM User u";
        TypedQuery<User> query = em.createQuery(queryString, User.class);
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<User> loadUsersByParam(User user) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User loadUserById(Integer userId) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateUser(User user) {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveUser(User user) {
    }
}
