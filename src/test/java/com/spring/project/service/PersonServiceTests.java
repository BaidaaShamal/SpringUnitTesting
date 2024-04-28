package com.spring.project.service;


import com.spring.project.model.dto.PersonDto;
import com.spring.project.model.entity.Person;
import com.spring.project.repository.PersonRepo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTests {

    @Mock
    private PersonRepo personRepo;

    @InjectMocks
    private PersonService personService;

    @Test
    public void personService_CreatePerson_ReturnsPersonDto() {
        Person person = Person.builder()
                .name("baidaa")
                .age(20).build();

        PersonDto personDto = PersonDto.builder().name("baidaa").age(20).build();

        when(personRepo.save(Mockito.any(Person.class))).thenReturn(person);

        PersonDto savedPerson = personService.save(personRepo.save(person));

        Assertions.assertThat(savedPerson).isNotNull();
    }



    @Test
    public void PersonService_FindById_ReturnPersonDto() {
        int personId = 1;
        Person person = Person.builder().id(1).name("baidaa").age(20).build();
        when(personRepo.findById(personId)).thenReturn(Optional.ofNullable(person));

        PersonDto personReturn = personService.getUser(personId);

        Assertions.assertThat(personReturn).isNotNull();
    }



    @Test
    public void PersonService_DeletePersonById_ReturnVoid() {
        int personId = 1;
        Person person = Person.builder().id(1).name("baidaa").age(20).build();
        when(personRepo.findById(personId)).thenReturn(Optional.ofNullable(person));

        doNothing().when(personRepo).delete(person);

       assertAll(() -> personService.delete(personId));

   }




}
