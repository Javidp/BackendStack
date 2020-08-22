package com.jd.backend.rest.model.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserQueryRepository implements IUserQueryRepository {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public Optional<User> findOne(long id) {
        return usersRepository.findById(id);
    }

    @Override
    public List<User> findAll() {
        return usersRepository.findAll();
    }

}
