package org.dzmitry.rsocket.client.request;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dzmitry.common.model.Player;
import org.dzmitry.common.model.Race;
import org.springframework.messaging.rsocket.RSocketRequester;

import javax.annotation.PostConstruct;

@Slf4j
@AllArgsConstructor
public class RequestStreamRSocketClient {

    private final RSocketRequester requester;

    @PostConstruct
    void requestStream() {
        log.info("request-stream client invocation is triggered!");
        requester.route("requestStream")
                .data(Race.HUMAN)
                .retrieveFlux(Player.class)
                .subscribe(player -> log.info("Players from server:{}", player));
    }
}
