package com.cvs.poc.SpringMongoPoc.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseSequenceTest {

    @Test
    public void testDatabaseSequence(){
        DatabaseSequence sequence = new DatabaseSequence();
        sequence.setId("101");
        sequence.setSeq(1111l);

        Assertions.assertEquals("101", sequence.getId());
        Assertions.assertEquals(1111l, sequence.getSeq());
    }
}