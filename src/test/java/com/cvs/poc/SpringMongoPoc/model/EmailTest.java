package com.cvs.poc.SpringMongoPoc.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmailTest {

    @Test
    public void testEmailObject(){
        Email email=new Email("abc@gmail.com","personal");
        Assertions.assertEquals("abc@gmail.com", email.getEmailAddress());
        Assertions.assertEquals("personal", email.getCategory());
    }

}