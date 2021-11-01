package org.dzmitry.rsocket.client.config;

import io.rsocket.RSocket;
import io.rsocket.RSocketFactory;
import io.rsocket.transport.netty.client.TcpClientTransport;
import lombok.AllArgsConstructor;
import org.dzmitry.rsocket.client.GameRSocketClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class ClientApplicationConfig {

    private final GameServerProperties serverProperties;

    @Bean
    GameRSocketClient gameRSocketClient() {
        RSocket rSocketClient = RSocketFactory.connect()
                .transport(TcpClientTransport
                        .create(serverProperties.getAddress(), serverProperties.getPort()))
                .start()
                .block();

        return new GameRSocketClient(rSocketClient);
    }

}
