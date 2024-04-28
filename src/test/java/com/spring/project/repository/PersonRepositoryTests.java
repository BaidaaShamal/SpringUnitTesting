package com.spring.project.repository;


import com.spring.project.model.entity.Person;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class PersonRepositoryTests {

    @Autowired
    private PersonRepo personRepo;

    @Test
    public void personRepository_Save_ReturnSavedPerson() {

        Person person = Person.builder()
                .name("baidaa")
                .age(12).build();

        Person savedPerson = personRepo.save(person);

        Assertions.assertThat(savedPerson).isNotNull();
        Assertions.assertThat(savedPerson.getId()).isGreaterThan(0);
    }



    @Test
    public void personRepository_GetAll_ReturnMoreThenOnePerson() {
        Person p = Person.builder()
                .name("baidaa")
                .age(20).build();
        Person p2 = Person.builder()
                .name("nada")
                .age(25).build();

        personRepo.save(p);
        personRepo.save(p2);

        List<Person> personList = personRepo.findAll();

        Assertions.assertThat(personList).isNotNull();
        Assertions.assertThat(personList.size()).isEqualTo(2);
    }

    @Test
    public void PersonRepository_FindById_ReturnPerson() {
        Person person = Person.builder()
                .name("baidaa")
                .age(20).build();

        personRepo.save(person);

        Person personList = personRepo.findById(person.getId()).get();

        Assertions.assertThat(personList).isNotNull();
    }


    @Test
    public void personRepository_FindByName_ReturnPersonNotNull() {
        Person person = Person.builder()
                .name("baidaa")
                .age(20).build();

        personRepo.save(person);

        Person personList = personRepo.findByName(person.getName()).get();

        Assertions.assertThat(personList).isNotNull();
    }


    @Test
    public void PersonRepository_UpdatePerson_ReturnPersonNotNull() {
        Person person = Person.builder()
                .name("baidaa")
                .age(20).build();

        personRepo.save(person);

        Person personSave = personRepo.findById(person.getId()).get();
        personSave.setAge(25);
       personSave.setName("nadaa");

        Person updatedPerson = personRepo.save(personSave);

        Assertions.assertThat(updatedPerson.getName()).isNotNull();
        Assertions.assertThat(updatedPerson.getAge()).isNotZero();
    }

    @Test
    public void PersonRepository_PersonDelete_ReturnPersonIsEmpty() {

        Person person = Person.builder()
                .name("baidaa")
                .age(20).build();

        personRepo.save(person);

        personRepo.deleteById(person.getId());
        Optional<Person> personReturn = personRepo.findById(person.getId());

        Assertions.assertThat(personReturn).isEmpty();
    }




}
