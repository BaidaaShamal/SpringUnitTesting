package com.spring.project.controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.project.model.dto.PersonDto;
import com.spring.project.model.entity.Person;
import com.spring.project.service.PersonService;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;




@WebMvcTest(controllers = PersonController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(value = MockitoExtension.class)
public class PersonControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonService personService;

    @Autowired
    private ObjectMapper objectMapper;
    private Person person ;
    private PersonDto personDto;

    @BeforeEach
    public void init() {
        person = Person.builder().name("baidaa").age(20).build();
        personDto = PersonDto.builder().name("baidaa").age(20).build();

    }

    @Test
    public void PersonController_CreatePerson_ReturnCreated() throws Exception {
        given(personService.createPerson(ArgumentMatchers.any())).willAnswer((invocation -> invocation.getArgument(0)));

        ResultActions response = mockMvc.perform(post("/person/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(personDto)));

        response.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(personDto.getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.age", CoreMatchers.is(personDto.getAge())));
    }


    @Test
    public void PersonController_UpdatePerson_ReturnPersonDto() throws Exception {

        when(personService.createPerson(personDto)).thenReturn(personDto);

        ResultActions response = mockMvc.perform(post("/person/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(personDto)));
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(personDto.getName())));

    }

    @Test
    public void testPrintName() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/person/print"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Ali"));
    }

    @Test
    public void PersonController_DeletePerson_ReturnString() throws Exception {
        Integer id = 1;

        mockMvc.perform(MockMvcRequestBuilders.get("/person/delete")
                        .param("id", id.toString()))
                .andExpect(status().isOk());

        verify(personService, times(1)).delete(id);
    }






}
