package com.hormigo.david.parkingmanager.user.service;


import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import java.util.List;
import java.util.ArrayList;
import com.hormigo.david.parkingmanager.core.exceptions.UserExistsException;
import com.hormigo.david.parkingmanager.user.domain.*;

public class UserServiceImplTest {
    @Test
    void testGetAll() {
        List<User> expected = new ArrayList<>();
        expected.add(new User("david@correo","David","Hormigo","Ramirez",Role.PROFESSOR));
        UserRepository mockedRepository = mock(UserRepository.class);
        when(mockedRepository.findAll()).thenReturn(expected);
        UserService service = new UserServiceImpl(mockedRepository);
        List<User> actual = (List<User>) service.getAll();
        assertEquals(expected,actual);
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

}
