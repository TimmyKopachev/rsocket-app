package org.dzmitry.rsocket.client;

import io.rsocket.RSocket;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GameRSocketClient {

    private final RSocket socket;

    public RSocket getSocket() {
        return socket;
    }

    public void dispose() {
        this.socket.dispose();
    }
}
