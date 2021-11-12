package com.cvs.poc.SpringMongoPoc.model;

import javax.validation.constraints.NotEmpty;

public class Email {

    @javax.validation.constraints.Email(message = "Email is not valid", regexp = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")
    @NotEmpty(message = "Email cannot be empty")
    private String emailAddress;
    private String category;

    public Email(String emailAddress, String category) {
        this.emailAddress = emailAddress;
        this.category = category;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "Email{" +
                "emailAddress='" + emailAddress + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
