
package com.java.domain;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "occupation")

@NamedQueries({
@NamedQuery(name = "Occupation.getAll", query = "SELECT c from Occupation c"),
@NamedQuery(name = "Occupation.getById", query = "SELECT c from Occupation c where c.id=:id")
}) 
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Occupation {

    
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id=0;
    
    @Column(name = "symbol")
    private String symbol;

    @Column(name = "value")
    private String value;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


}

