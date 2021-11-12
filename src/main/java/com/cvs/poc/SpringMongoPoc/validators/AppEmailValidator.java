package com.cvs.poc.SpringMongoPoc.validators;


import org.apache.commons.validator.EmailValidator;
import org.springframework.stereotype.Component;

@Component
public class AppEmailValidator {
    public boolean isValidEmail(String email){
        //EmailValidator validator = org.apache.commons.validator.EmailValidator.getInstance();
        EmailValidator validator = EmailValidator.getInstance();
        return validator.isValid(email);
    }
}
