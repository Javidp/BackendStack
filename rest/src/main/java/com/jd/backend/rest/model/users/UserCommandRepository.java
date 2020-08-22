package com.jd.backend.rest.model.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserCommandRepository implements IUserCommandRepository {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public void save(User user) {
        usersRepository.save(user);
    }

}
