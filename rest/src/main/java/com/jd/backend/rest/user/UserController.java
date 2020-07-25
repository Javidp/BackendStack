package com.jd.backend.rest.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/user/show")
    public String showUser() {
        return "username: user1, password: password1";
    }

}
