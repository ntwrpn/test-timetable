package com.java.domain;

import javax.validation.ConstraintViolation;
import java.sql.Timestamp;
import java.util.Set;

public class Response {

    private String message;

    private Timestamp timestamp;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Timestamp getTimestamp(Set<ConstraintViolation> violations) {

        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
