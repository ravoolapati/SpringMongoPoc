package com.cvs.poc.SpringMongoPoc.validators;

import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class PhoneNumberValidator {
    private final String patterns
            =  "^\\d{10}$"
            + "|^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$"
            + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?){2}\\d{3}$"
            + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?)(\\d{2}[ ]?){2}\\d{2}$";

    public boolean validate(String countryCode, String phoneNumber){
        String completeNumber = phoneNumber;
        Pattern pattern = Pattern.compile(patterns);
        Matcher matcher = pattern.matcher(completeNumber);
        return matcher.matches();
    }
}
