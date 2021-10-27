package com.cvs.poc.SpringMongoPoc.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.annotation.Generated;
import java.time.LocalDate;
import java.util.List;

@Document(collection ="person")
public class Person {

    public static final String SEQUENCE_NAME = "person_sequence";
    @Id
    private Long id;
    private String firstname;
    private String lastname;
    private String genderBio;
    private String birthdate;
    private String preferedModeOfContact;
    private List<Address> addresses;
    private List<Phone> phones;
    private List<Email> emails;

    public Person(String firstname, String lastname, String genderBio, String birthdate,
                  String preferedModeOfContact, List<Address> addresses, List<Phone> phones, List<Email> emails) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.genderBio = genderBio;
        this.birthdate = birthdate;
        this.preferedModeOfContact = preferedModeOfContact;
        this.addresses = addresses;
        this.phones = phones;
        this.emails = emails;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getGenderBio() {
        return genderBio;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public String getPreferedModeOfContact() {
        return preferedModeOfContact;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public List<Phone> getPhones() {
        return phones;
    }

    public List<Email> getEmails() {
        return emails;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setGenderBio(String genderBio) {
        this.genderBio = genderBio;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public void setPreferedModeOfContact(String preferedModeOfContact) {
        this.preferedModeOfContact = preferedModeOfContact;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }

    public void setEmails(List<Email> emails) {
        this.emails = emails;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
