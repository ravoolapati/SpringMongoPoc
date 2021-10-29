package com.cvs.poc.SpringMongoPoc.model;

import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddressTest {

    @Test
    public void testAddressObject(){
        Address address = new Address("101 street1", "seattle", "washington", "456123", "US");

        Assertions.assertEquals("101 street1", address.getStreetAddress());
        Assertions.assertEquals("seattle", address.getCity());
        Assertions.assertEquals("washington", address.getState());
        Assertions.assertEquals("456123", address.getZip());
        Assertions.assertEquals("US", address.getCountry());
    }

}