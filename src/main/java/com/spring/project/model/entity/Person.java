package com.spring.project.model.entity;

import com.spring.project.model.dto.PersonDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="person")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private  String name;
    private int age;

    public static Person toEntity(PersonDto personDto){
        return Person.builder()
                .id(personDto.getId())
                .name(personDto.getName())
                .build();
    }
}
