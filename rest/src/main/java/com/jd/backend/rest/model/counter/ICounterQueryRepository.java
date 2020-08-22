package com.jd.backend.rest.model.counter;

import java.util.Optional;

public interface ICounterQueryRepository {

    Optional<Counter> getOne(String id);

}
