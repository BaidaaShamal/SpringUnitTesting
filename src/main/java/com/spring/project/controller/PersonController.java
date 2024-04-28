package com.spring.project.controller;

import com.spring.project.model.dto.PersonDto;
import com.spring.project.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/person")
public class PersonController {

   /* @Autowired
    private PersonService personService;*/

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }


  /*  @PostMapping("/save")
    public PersonDto savePerson(@RequestBody Person p) {
        return personService.save(p);
    }*/

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PersonDto> createPerson(@RequestBody PersonDto pokemonDto) {
        return new ResponseEntity<>(personService.createPerson(pokemonDto), HttpStatus.CREATED);
    }

    @GetMapping("/delete")
    public void delete(@RequestParam Integer id) {
         personService.delete(id);
    }

    @PostMapping("/update")
    public PersonDto updatePerson(@RequestBody PersonDto p) {
        return personService.createPerson(p);
    }

    @GetMapping("/print")
    public String printName(){
        return "Ali";
    }


    @GetMapping("/user")
    public ResponseEntity<PersonDto> getUser(@RequestParam Integer id) {
        PersonDto personDto = personService.getUser(id);
        if (personDto != null) {
            return ResponseEntity.ok(personDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
