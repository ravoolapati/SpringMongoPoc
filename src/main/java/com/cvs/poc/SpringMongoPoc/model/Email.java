package com.cvs.poc.SpringMongoPoc.model;

public class Email {
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
}
