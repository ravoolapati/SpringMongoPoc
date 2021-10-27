package com.cvs.poc.SpringMongoPoc.controller;

import static org.assertj.core.api.Assertions.assertThat;

import com.cvs.poc.SpringMongoPoc.model.Address;
import com.cvs.poc.SpringMongoPoc.model.Email;
import com.cvs.poc.SpringMongoPoc.model.Person;
import com.cvs.poc.SpringMongoPoc.model.Phone;
import com.cvs.poc.SpringMongoPoc.repository.PersonRepository;
import com.cvs.poc.SpringMongoPoc.services.SequenceGeneratorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;


@WebMvcTest(PersonController.class)
class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonRepository personRepository;

    @MockBean
    private SequenceGeneratorService sequenceGeneratorService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void getAllPersonsTest() throws Exception {
        List<Person> persons =  Arrays.asList(getPerson());
        Mockito.when(personRepository.findAll()).thenReturn(persons);
        String response = this.mockMvc.perform(MockMvcRequestBuilders.get("/api/persons"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();
        List<Person> objects = objectMapper.readValue(response, List.class);
        Assertions.assertTrue(objects.size() == 1);
    }

    private Person getPerson(){

        String firstName = "firstName";
        String lastName  = "lastName";
        String genderBio ="M";
        String brithDate = "10-10-2021";
        String preferedModelOfContact = "phone";
        List<Address> address = Arrays.asList(new Address("101","NYC","NYC","122211","US"));
        List<Phone> phones = Arrays.asList(new Phone("+1","42252555","office","865656565656"));
        List<Email> emails = Arrays.asList(new Email("abc@gmail.com","personal"));
        Person person = new Person(firstName, lastName, genderBio,brithDate,preferedModelOfContact,
                address,phones
        , emails);
        person.setId(101);
        return person;
    }

}