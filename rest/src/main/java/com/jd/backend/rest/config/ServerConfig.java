package com.jd.backend.rest.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServerConfig {

    @Value("${server.port}")
    private int serverPort;

    public int getServerPort() {
        return serverPort;
    }

}
