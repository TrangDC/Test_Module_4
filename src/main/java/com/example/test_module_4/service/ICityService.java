package com.example.test_module_4.service;

import com.example.test_module_4.model.City;

import java.util.Optional;

public interface ICityService {

    Iterable<City> findAll();
    Optional<City> findById(Long id);
    void save (City city);
    void remove(Long id);

    Iterable<City> findByName(String name);
}
