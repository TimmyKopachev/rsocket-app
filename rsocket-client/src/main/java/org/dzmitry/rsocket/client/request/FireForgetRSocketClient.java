package org.dzmitry.rsocket.client.request;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dzmitry.common.request.ChangePlayerNameRequest;
import org.springframework.messaging.rsocket.RSocketRequester;

import javax.annotation.PostConstruct;

@Slf4j
@AllArgsConstructor
public class FireForgetRSocketClient {

    private final RSocketRequester requester;

    @PostConstruct
    void fireAndForget() {
        log.info("fire and forget client invocation is triggered!");
        requester.route("fireAndForget")
                .data(new ChangePlayerNameRequest("Sergey Petrovsky", "Viktoria Petrovskya"))
                .send()
                .subscribe(player -> log.info("Players from server:{}", player));
    }
}
