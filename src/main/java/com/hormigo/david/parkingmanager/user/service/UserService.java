package com.hormigo.david.parkingmanager.user.service;

import com.hormigo.david.parkingmanager.user.UserAlreadyExistsException;
import com.hormigo.david.parkingmanager.user.domain.User;
import com.hormigo.david.parkingmanager.user.domain.UserDao;

public interface UserService {
    public Iterable<User> getAll();
    public void register(UserDao userDao) throws UserAlreadyExistsException;
}
