package org.dzmitry.rsocket.server.config;

import io.rsocket.ConnectionSetupPayload;
import io.rsocket.RSocket;
import io.rsocket.SocketAcceptor;
import org.dzmitry.rsocket.server.service.RSocketServiceImpl;
import reactor.core.publisher.Mono;

public class CustomRSocketAcceptor implements SocketAcceptor {

    @Override
    public Mono<RSocket> accept(ConnectionSetupPayload connectionSetupPayload, RSocket rSocket) {
        return Mono.just(new RSocketServiceImpl());
    }
}
