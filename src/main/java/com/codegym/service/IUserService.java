package com.codegym.service;

import com.codegym.model.User;

public interface IUserService {
    Iterable<User> findAll();

    User findById(Long id);

    void save(User user);

    void remove(Long id);
}
