package com.fsm.backend.Objects.Valuable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fsm.backend.Enums.Status;
import com.fsm.backend.Interfaces.MyObject;

import java.util.UUID;

public class Valuable implements MyObject {

    private UUID id;
    private String name;
    private int initialPrice;
    //TODO Utilize status variable (sold, not sold)
    private Status status;

    @JsonCreator
    public Valuable(@JsonProperty("name") String name,
                    @JsonProperty("initialPrice") int initialPrice) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.initialPrice = initialPrice;
        status = Status.NOTSOLD;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getInitialPrice() {
        return initialPrice;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setInitialPrice(int initialPrice) {
        this.initialPrice = initialPrice;
    }
}
