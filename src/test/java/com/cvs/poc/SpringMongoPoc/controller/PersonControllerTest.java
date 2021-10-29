package com.cvs.poc.SpringMongoPoc.controller;


import com.cvs.poc.SpringMongoPoc.model.Address;
import com.cvs.poc.SpringMongoPoc.model.Email;
import com.cvs.poc.SpringMongoPoc.model.Person;
import com.cvs.poc.SpringMongoPoc.model.Phone;
import com.cvs.poc.SpringMongoPoc.repository.PersonRepository;
import com.cvs.poc.SpringMongoPoc.services.SequenceGeneratorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.*;


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

    @Test
    public void getPersonByIdTest() throws Exception {
        Optional<Person> person =  Optional.of(getPerson());
        Mockito.when(personRepository.findById(101l)).thenReturn(person);
        String response = this.mockMvc.perform(MockMvcRequestBuilders.get("/api/person/101"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();
        Person object = objectMapper.readValue(response, Person.class);
        Assertions.assertEquals(101l, object.getId());
    }

    @Test
    public void createPersonTest() throws Exception {
        Person person =  getPerson();

        String personJson = objectMapper.writeValueAsString(person);
        Mockito.when(sequenceGeneratorService.generateSequence(Person.SEQUENCE_NAME)).thenReturn(101l);
        Mockito.when(personRepository.save(Matchers.any(Person.class))).thenReturn(person);
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post("/api/person/").contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(personJson)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        System.out.print("content :"+personJson);
        Assertions.assertEquals(personJson, content);
    }

    @Test
    public void updatePersonTest() throws Exception {
        Person person =  getPerson();
        Mockito.when(personRepository.findById(101l)).thenReturn(Optional.of(person));
        person.setGenderBio("F");
        String personJson = objectMapper.writeValueAsString(person);
        Mockito.when(personRepository.save(person)).thenReturn(person);
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.patch("/api/person/101").contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(personJson)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Person object = objectMapper.readValue(content, Person.class);
        Assertions.assertEquals("F",object.getGenderBio());
    }

    @Test
    public void deletePersonTest() throws Exception {
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        Person person =  getPerson();
        Mockito.when(personRepository.findById(101l)).thenReturn(Optional.of(person));
        String result = this.mockMvc.perform(MockMvcRequestBuilders.delete("/api/person/101"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();
        Assertions.assertNotNull(result);

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