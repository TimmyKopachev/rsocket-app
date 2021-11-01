package org.dzmitry.rsocket.server.config;

import io.rsocket.RSocketFactory;
import io.rsocket.transport.netty.server.TcpServerTransport;
import lombok.AllArgsConstructor;
import org.dzmitry.rsocket.server.GameRSocketServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.Disposable;

@Configuration
@AllArgsConstructor
public class GameServerConfiguration {

    private final GameServerProperties serverProperties;

    @Bean
    GameRSocketServer gameRSocketServer() {
        Disposable disposable = RSocketFactory.receive()
                .acceptor(new CustomRSocketAcceptor())
                .transport(TcpServerTransport
                        .create(serverProperties.getAddress(), serverProperties.getPort()))
                .start()
                .subscribe();
        return new GameRSocketServer(disposable);
    }
    
}
