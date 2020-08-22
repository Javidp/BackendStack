package com.jd.backend.rest.counter;

public class IncrementCounterParameters {

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "IncrementCounterParameters{" +
                "id='" + id + '\'' +
                '}';
    }

}
