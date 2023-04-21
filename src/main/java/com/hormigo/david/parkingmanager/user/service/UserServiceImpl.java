package com.hormigo.david.parkingmanager.user.service;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.hormigo.david.parkingmanager.user.UserAlreadyExistsException;
import com.hormigo.david.parkingmanager.user.domain.User;
import com.hormigo.david.parkingmanager.user.domain.UserDao;
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

    public void register(UserDao userDao) throws UserAlreadyExistsException {
        if (userExists(userDao.getEmail())){
            throw new UserAlreadyExistsException();
        }
        User user = new User();
        
        BeanUtils.copyProperties(userDao, user);
        this.repository.save(user);
    }

    private boolean userExists(String email) {
        return this.repository.findByEmail(email)!=null ? true : false;
    }

}
