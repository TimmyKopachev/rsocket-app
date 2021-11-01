package org.dzmitry.rsocket.server;

import lombok.AllArgsConstructor;
import reactor.core.Disposable;


@AllArgsConstructor
public class GameRSocketServer {

    private final Disposable server;

    public void dispose() {
        this.server.dispose();
    }
}
