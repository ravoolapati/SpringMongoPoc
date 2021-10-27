package com.cvs.poc.SpringMongoPoc.controller;

import com.cvs.poc.SpringMongoPoc.exceptions.ResourceNotFoundException;
import com.cvs.poc.SpringMongoPoc.model.Person;
import com.cvs.poc.SpringMongoPoc.repository.PersonRepository;
import com.cvs.poc.SpringMongoPoc.services.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/")
public class PersonController {

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    @Autowired
    private PersonRepository personRepository;

    @RequestMapping("/persons")
    public List<Person> getAllPersons(){
        return personRepository.findAll();
    }

    @GetMapping("/person/{id}")
    public ResponseEntity<Person> findPersonById(@PathVariable(value="id") Long id) throws ResourceNotFoundException {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Person not found with id "+id));
        return ResponseEntity.ok().body(person);
    }

    @PostMapping("/person")
    public Person createPerson(@RequestBody Person person){
        person.setId(sequenceGeneratorService.generateSequence(Person.SEQUENCE_NAME));
        return personRepository.save(person);
    }

    @PatchMapping("/person/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable(value="id") Long id, @RequestBody Person person) throws ResourceNotFoundException {
        Person existing = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Person not found with id "+id));
        existing.setBirthdate(person.getBirthdate());
        existing.setFirstname(person.getFirstname());
        existing.setLastname(person.getLastname());
        existing.setGenderBio(person.getGenderBio());
        existing.setPreferedModeOfContact(person.getPreferedModeOfContact());
        final Person updated = personRepository.save(existing);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/person/{id}")
    public Map<String, Boolean> deletePerson(@PathVariable(value="id") Long id) throws ResourceNotFoundException {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Person not found with id "+id));
        personRepository.delete(person);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
