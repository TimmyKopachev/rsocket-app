package org.dzmitry.rsocket.client.request;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dzmitry.common.model.Player;
import org.springframework.messaging.rsocket.RSocketRequester;
import reactor.core.publisher.Flux;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.util.Random;

@Slf4j
@AllArgsConstructor
public class ChannelRSocketClient {

    private final RSocketRequester requester;

    @PostConstruct
    void channel() {
        Random random = new Random();
        log.info("channel client invocation is triggered!");
        requester.route("channel")
                .data(Flux.just(random.nextInt(2)))
                /*.data(Flux.interval(Duration.ofSeconds(1))
                        .map((i) -> ))*/
                .retrieveFlux(Player.class)
                .subscribe(player -> log.info("Players from server:{}", player));
    }
}
