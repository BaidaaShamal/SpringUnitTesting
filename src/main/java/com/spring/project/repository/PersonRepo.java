package com.spring.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.spring.project.model.entity.Person;
import org.springframework.stereotype.Component;

import java.util.Optional;

public interface PersonRepo extends JpaRepository<Person,Integer> {
    Optional<Person> findByName(String name);

}

