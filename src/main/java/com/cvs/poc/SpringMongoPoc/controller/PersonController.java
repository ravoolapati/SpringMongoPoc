package com.cvs.poc.SpringMongoPoc.controller;

import com.cvs.poc.SpringMongoPoc.exceptions.InValidEmailAddressException;
import com.cvs.poc.SpringMongoPoc.exceptions.InValidPhoneNumberException;
import com.cvs.poc.SpringMongoPoc.exceptions.ResourceNotFoundException;
import com.cvs.poc.SpringMongoPoc.model.Email;
import com.cvs.poc.SpringMongoPoc.model.Person;
import com.cvs.poc.SpringMongoPoc.model.Phone;
import com.cvs.poc.SpringMongoPoc.repository.PersonRepository;
import com.cvs.poc.SpringMongoPoc.services.KafkaProducerService;
import com.cvs.poc.SpringMongoPoc.services.SequenceGeneratorService;
import com.cvs.poc.SpringMongoPoc.validators.AppEmailValidator;
import com.cvs.poc.SpringMongoPoc.validators.PhoneNumberValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @Autowired
    private KafkaProducerService kafkaProducerService;

    @Autowired
    private AppEmailValidator emailValidator;

    @Autowired
    private PhoneNumberValidator phoneNumberValidator;

    private Logger logger = LoggerFactory.getLogger(PersonController.class);

    @RequestMapping("/persons")
    public List<Person> getAllPersons(){
        logger.info("calling getAll");
        return personRepository.findAll();
    }

    @GetMapping("/person/{id}")
    public ResponseEntity<Person> findPersonById(@PathVariable(value="id") Long id) throws ResourceNotFoundException {
        logger.info(String.format("find person by id : %s", id));
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Person not found with id "+id));
        return ResponseEntity.ok().body(person);
    }

    @PostMapping("/person")
    public Person createPerson(@Valid @RequestBody Person person) throws InValidPhoneNumberException, InValidEmailAddressException {
        person.setId(sequenceGeneratorService.generateSequence(Person.SEQUENCE_NAME));

        validatePhoneNumber(person);
        validateEmailAddress(person);

        kafkaProducerService.savePerson(person);
        return person;
        //logger.info(String.format("Creating person : %s", person));
        //return personRepository.save(person);
    }

    private void validateEmailAddress(@RequestBody @Valid Person person) throws InValidEmailAddressException {
        for (Email email : person.getEmails()) {
            if (!emailValidator.isValidEmail(email.getEmailAddress())) {
                logger.info("invalid email id");
                throw new InValidEmailAddressException("email is invalid");
            }
        }
    }

    @PatchMapping("/person/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable(value="id") Long id, @RequestBody Person person) throws ResourceNotFoundException, InValidPhoneNumberException, InValidEmailAddressException {

        validatePhoneNumber(person);

        validateEmailAddress(person);


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

    private void validatePhoneNumber(@RequestBody Person person) throws InValidPhoneNumberException {
        for (Phone phone : person.getPhones()) {
            if (!phoneNumberValidator.validate(phone.getCountryCode(), phone.getMobile())) {
                logger.info("invalid phone number");

                throw new InValidPhoneNumberException(String.format("%s %s is not valid ", phone.getCountryCode(), phone.getMobile()));
            }
        }
    }

    @DeleteMapping("/person/{id}")
    public Map<String, Boolean> deletePerson(@PathVariable(value="id") Long id) throws ResourceNotFoundException {
        logger.info(String.format("delete person by id : %s", id));
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Person not found with id "+id));
        personRepository.delete(person);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
