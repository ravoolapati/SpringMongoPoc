package com.cvs.poc.SpringMongoPoc.model;

public class Phone {
    private String countryCode;
    private String number;
    private String category;
    private String mobile;

    public Phone(String countryCode, String number, String category, String mobile) {
        this.countryCode = countryCode;
        this.number = number;
        this.category = category;
        this.mobile = mobile;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getNumber() {
        return number;
    }

    public String getCategory() {
        return category;
    }

    public String getMobile() {
        return mobile;
    }
}
