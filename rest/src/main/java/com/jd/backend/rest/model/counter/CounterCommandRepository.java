package com.jd.backend.rest.model.counter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class CounterCommandRepository implements ICounterCommandRepository {

    @Autowired
    private CountersRepository countersRepository;

    @Override
    public void incrementCounter(String id, Timestamp date) {
        countersRepository.incrementCounter(id, date);
    }

}
