package com.jd.backend.rest.model.counter;

import java.sql.Timestamp;

public interface ICounterCommandRepository {

    void incrementCounter(String id, Timestamp date);

}
