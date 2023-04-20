package com.hormigo.david.parkingmanager.draw.service;

import org.springframework.stereotype.Service;

import com.hormigo.david.parkingmanager.draw.domain.Draw;
import com.hormigo.david.parkingmanager.draw.domain.DrawDao;
import com.hormigo.david.parkingmanager.draw.domain.DrawRepository;
@Service
public class DrawServiceImpl implements DrawService{

    DrawRepository repository;
    public DrawServiceImpl(DrawRepository repository) {
        this.repository = repository;
    }
    @Override
    public Iterable<Draw> getAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

    @Override
    public Draw getLastDrawed() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getLastDrawed'");
    }

    @Override
    public void register(DrawDao drawDao) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'register'");
    }
    
}
