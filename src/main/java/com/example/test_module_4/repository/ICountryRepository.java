package com.example.test_module_4.repository;

import com.example.test_module_4.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICountryRepository extends JpaRepository<Country, Long> {
    Iterable<Country> findAllByNameContainingIgnoreCase(String name);
}
