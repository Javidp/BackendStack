package com.jd.backend.rest.model.users;

import java.util.List;
import java.util.Optional;

public interface IUserQueryRepository {

    Optional<User> findOne(long id);
    List<User> findAll();

}
