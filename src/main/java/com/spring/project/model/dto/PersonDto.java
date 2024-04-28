package com.spring.project.model.dto;

import com.spring.project.model.entity.Person;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonDto {

    private Integer id;
    private  String name;
    private int age;


    public static PersonDto toDto(Person person){

        return PersonDto.builder()
                .id(person.getId())
                .name(person.getName())
                .build();

    }

}