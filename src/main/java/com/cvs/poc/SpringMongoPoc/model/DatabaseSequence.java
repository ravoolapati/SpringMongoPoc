package com.cvs.poc.SpringMongoPoc.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="database_sequence")
public class DatabaseSequence {
    @Id
    private String id;

    private long seq;

    public DatabaseSequence(){


    }

    public String getId() {
        return id;
    }

    public long getSeq() {
        return seq;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setSeq(long seq) {
        this.seq = seq;
    }
}
