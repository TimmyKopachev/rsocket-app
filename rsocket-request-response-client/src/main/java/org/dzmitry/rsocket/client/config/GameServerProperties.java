package org.dzmitry.rsocket.client.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "application.tcp.server.details")
public class GameServerProperties {

    private Integer port;
    private String address;

}
