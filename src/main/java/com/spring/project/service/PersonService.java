package com.spring.project.service;

import com.spring.project.model.dto.PersonDto;
import com.spring.project.model.entity.Person;
import com.spring.project.repository.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonService {

    /*@Autowired
    public PersonRepo personRepo;*/

    public final PersonRepo personRepo;

    public PersonService(PersonRepo personRepo) {
        this.personRepo = personRepo;
    }

    public PersonDto getUser(int id ){
        Optional<Person> person =this.personRepo.findById(id);

//return person.orElse(null);
        if(person.isPresent()) return PersonDto.toDto(person.get()) ;
        return null;
    }


    public PersonDto save(Person person){
    /*  return   this.personRepo.save(person);*/
        return PersonDto.toDto(personRepo.save(person));
    }


    public PersonDto createPerson(PersonDto personDto) {
        Person person = new Person();
        person.setId(personDto.getId());
        person.setName(personDto.getName());
        person.setAge(personDto.getAge());

        Person newPerson = personRepo.save(person);

        PersonDto personResponse = new PersonDto();
        personResponse.setId(newPerson.getId());
        personResponse.setName(newPerson.getName());
        personResponse.setAge(newPerson.getAge());
        return personResponse;
    }



    public void delete(Integer id){
        Person person = personRepo.findById(id).orElseThrow(() -> new RuntimeException("Pokemon could not be delete"));

        personRepo.delete(person);
    }






}
