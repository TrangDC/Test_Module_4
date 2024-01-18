package com.example.test_module_4.service;

import com.example.test_module_4.model.City;
import com.example.test_module_4.model.Country;

import java.util.Optional;

public interface ICountryService {
    Iterable<Country> findAll();
    Optional<Country> findById(Long id);
    void save (Country country);
    void remove(Long id);

    Iterable<Country> findByName(String name);
}
