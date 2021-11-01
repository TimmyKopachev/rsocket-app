package org.dzmitry.rsocket;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class ServerClientApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .sources(ServerClientApplication.class)
                .bannerMode(Banner.Mode.OFF)
                .run(args);
    }
}
