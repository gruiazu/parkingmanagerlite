package com.hormigo.david.parkingmanager.user.service;

import org.springframework.stereotype.Service;

import com.hormigo.david.parkingmanager.user.domain.User;
import com.hormigo.david.parkingmanager.user.domain.UserRepository;
@Service
public class UserServiceImpl implements UserService {

    private UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public Iterable<User> getAll() {

        return this.repository.findAll();
    }

}
