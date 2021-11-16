package org.dzmitry.rsocket;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class ClientApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .sources(ClientApplication.class)
                .bannerMode(Banner.Mode.OFF)
                .run(args);
    }
}
