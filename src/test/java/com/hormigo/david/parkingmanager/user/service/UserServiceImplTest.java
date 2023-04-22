package com.hormigo.david.parkingmanager.user.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.hormigo.david.parkingmanager.core.exceptions.UserExistsException;
import com.hormigo.david.parkingmanager.user.domain.Role;
import com.hormigo.david.parkingmanager.user.domain.User;
import com.hormigo.david.parkingmanager.user.domain.UserDao;
import com.hormigo.david.parkingmanager.user.domain.UserRepository;

public class UserServiceImplTest {
    @Test
    void testGetAll() {
        final List<User> expected = new ArrayList<>();
        expected.add(new User("david@correo","David","Hormigo","Ramirez",Role.PROFESSOR));
        final UserRepository mockedRepository = mock(UserRepository.class);
        when(mockedRepository.findAll()).thenReturn(expected);
        final UserService service = new UserServiceImpl(mockedRepository);
        final List<User> actual = (List<User>) service.getAll();
        assertEquals(expected,actual);
    }

    @Test
    void testUserAlreadyExists() {
        final User user = new User("david@correo","David","Hormigo","Ramírez",Role.PROFESSOR);
        final UserRepository mockedRepository = mock(UserRepository.class);
        when(mockedRepository.findByEmail("david@correo")).thenReturn(user);
        final UserService service = new UserServiceImpl(mockedRepository);

        final UserDao userDao = new UserDao("david@correo", "David","Hormigo", "Ramírez", Role.PROFESSOR);
    
        assertThrows(UserExistsException.class,()->{service.register(userDao);});

    }

    @Test
    void testUserDoesNotExists() {
        //User user = new User("david@correo","David","Hormigo","Ramírez",Role.PROFESSOR);
        final UserRepository mockedRepository = mock(UserRepository.class);
        when(mockedRepository.findByEmail("david@correo")).thenReturn(null);
        final UserService service = new UserServiceImpl(mockedRepository);

        final UserDao userDao = new UserDao("david@correo", "David","Hormigo", "Ramírez", Role.PROFESSOR);
        try {
        service.register(userDao);
        }
        catch (final UserExistsException e) {
            fail("Ha lanzaado la excepción");
        }
        verify(mockedRepository).save(any(User.class));

    }

}
