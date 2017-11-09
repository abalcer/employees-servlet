package com.brainacademy.service.dao;

import com.brainacademy.service.model.User;

public interface UserDao extends GenericDao<User> {
    User findOneByEmail(String email);
}
