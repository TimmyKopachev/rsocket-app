package org.dzmitry.rsocket.client.request;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dzmitry.common.model.Player;
import org.dzmitry.common.model.Race;
import org.springframework.messaging.rsocket.RSocketRequester;

import javax.annotation.PostConstruct;

@Slf4j
@AllArgsConstructor
public class RequestResponseRSocketClient {

    private final RSocketRequester requester;

    @PostConstruct
    void requestResponse() {
        log.info("request-response client invocation is triggered!");
        requester.route("requestResponse")
                .data(Race.DARK_ELF)
                .retrieveMono(Player.class)
                .subscribe(player -> log.info("Player from server:{}", player));
    }
}
