package com.jd.backend.rest.user;

import com.jd.backend.rest.config.ServerConfig;
import com.jd.backend.rest.model.users.User;
import com.jd.backend.rest.model.users.UsersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;

@RestController
public class UsersRestController {

    private final Logger logger = LoggerFactory.getLogger(getClass().getName());

    @Autowired
    private ServerConfig serverConfig;
    @Autowired
    private UsersRepository usersRepository;

    @PostMapping("/user/add")
    public String addUser(@RequestBody AddUserParameters addUserParameters) {
        User user = new User();
        user.setUsername(addUserParameters.getUsername());
        user.setPassword(addUserParameters.getPassword());
        user.setTimestamp(new Timestamp(System.currentTimeMillis()));

        usersRepository.save(user);

        return user.toString();
    }

    @PostMapping("/users/run")
    public String runAddingUsers(@RequestParam("number") long number) {
        new Thread(() -> {
            for (int i=0; i<number; i++) {
                User user = new User();
                user.setUsername("user_" + serverConfig.getServerPort() + "_" + i);
                user.setPassword("pass");
                user.setTimestamp(new Timestamp(System.currentTimeMillis()));

                usersRepository.save(user);
                logger.info("Added new user: " + user);
            }
        }).start();

        return "OK";
    }

}
