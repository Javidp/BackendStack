package com.jd.backend.rest.counter;

public class IncrementCounterRunParameters {

    private String id;
    private long limit;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getLimit() {
        return limit;
    }

    public void setLimit(long limit) {
        this.limit = limit;
    }

    @Override
    public String toString() {
        return "IncrementCounterRunParameters{" +
                "id='" + id + '\'' +
                ", limit=" + limit +
                '}';
    }

}
