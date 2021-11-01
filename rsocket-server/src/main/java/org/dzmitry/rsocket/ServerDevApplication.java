package org.dzmitry.rsocket;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class ServerDevApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .sources(ServerDevApplication.class)
                .bannerMode(Banner.Mode.OFF)
                .run(args);
    }
}
