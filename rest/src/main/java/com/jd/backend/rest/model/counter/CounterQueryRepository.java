package com.jd.backend.rest.model.counter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CounterQueryRepository implements ICounterQueryRepository {

    @Autowired
    private CountersRepository countersRepository;

    @Override
    public Optional<Counter> getOne(String id) {
        return countersRepository.findById(id);
    }

}
