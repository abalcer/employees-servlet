package com.brainacademy.service.dao.impl;

import com.brainacademy.service.dao.UserDao;
import com.brainacademy.service.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

@Service
public class HbUserDaoImpl extends AbstractDao<User> implements UserDao {

    @Autowired
    public HbUserDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    protected Class<User> getEntityType() {
        return User.class;
    }

    @Override
    public User findOneByEmail(String email) {
        try (Session session = sessionFactory.openSession()) {
            TypedQuery<User> query = session.createQuery(
                    "from User where email=:email", User.class)
                    .setParameter("email", email);

            List<User> resultList = query.getResultList();
            if (resultList == null || resultList.isEmpty()) {
                return null;
            }
            return resultList.get(0);
        } catch (NoResultException e) {
            return null;
        }
    }
}
