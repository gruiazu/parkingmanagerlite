package com.hormigo.david.parkingmanager.user.service;


import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import com.hormigo.david.parkingmanager.core.exceptions.UserExistsException;
import com.hormigo.david.parkingmanager.user.domain.*;

public class UserServiceImplTest {
    @Test
    void testGetAll() {

    }

    @Test
    void testUserAlreadyExists() {
        User user = new User("david@correo","David","Hormigo","Ramírez",Role.PROFESSOR);
        UserRepository mockedRepository = mock(UserRepository.class);
        when(mockedRepository.findByEmail("david@correo")).thenReturn(user);
        UserService service = new UserServiceImpl(mockedRepository);

        UserDao userDao = new UserDao("david@correo", "David","Hormigo", "Ramírez", Role.PROFESSOR);
    
        assertThrows(UserExistsException.class,()->{service.register(userDao);});

    }

    @Test
    void testUserDoesNotExists() {
        //User user = new User("david@correo","David","Hormigo","Ramírez",Role.PROFESSOR);
        UserRepository mockedRepository = mock(UserRepository.class);
        when(mockedRepository.findByEmail("david@correo")).thenReturn(null);
        UserService service = new UserServiceImpl(mockedRepository);

        UserDao userDao = new UserDao("david@correo", "David","Hormigo", "Ramírez", Role.PROFESSOR);
        try {
        service.register(userDao);
        }
        catch (UserExistsException e) {
            fail("Ha lanzaado la excepción");
        }
        verify(mockedRepository).save(any(User.class));

    }

    @Test
    void testName() {
        
    }
}
