package com.java.config;

import com.java.domain.Response;

import javax.validation.ConstraintViolation;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;

public class ExceptionResponceCreator {

    public static Response createResponse(Set<ConstraintViolation<?>> violations){
        StringBuilder st = new StringBuilder();
        for(ConstraintViolation e: violations){
            st.append(e.getMessage());
            break;
        }
        Response response = new Response();
        Date date= new Date();
        long time = date.getTime();
        response.setTimestamp(new Timestamp(time));
        response.setMessage(st.toString());
        return response;
    }
}
