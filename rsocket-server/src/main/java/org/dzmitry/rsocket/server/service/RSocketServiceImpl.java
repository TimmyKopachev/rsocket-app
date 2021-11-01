package org.dzmitry.rsocket.server.service;

import io.rsocket.AbstractRSocket;
import io.rsocket.Payload;
import io.rsocket.util.DefaultPayload;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.dzmitry.common.model.Attribute;
import org.dzmitry.common.model.Hero;
import org.dzmitry.common.model.Player;
import org.dzmitry.common.model.event.EventType;
import org.dzmitry.common.request.PlayerEventRequest;
import org.dzmitry.common.utils.PayloadTransformation;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@AllArgsConstructor
public class RSocketServiceImpl extends AbstractRSocket {

    @SneakyThrows
    @Override
    public Mono<Payload> requestResponse(Payload payload) {
        PayloadTransformation<PlayerEventRequest> playerEventTransform = new PayloadTransformation<>(PlayerEventRequest.class);
        PlayerEventRequest playerEventRequest = playerEventTransform.transformByteArrayToData(payload.getData().array());
        log.info("Player event: {} is received for player: {}", playerEventRequest.getEventType(), playerEventRequest.getPlayer());
        // do changes with player
        Player updated = playerEventRequestHandler(playerEventRequest).getPlayer();
        log.info("Server updates player: {}", updated);
        PayloadTransformation<Player> playerTransform = new PayloadTransformation<>(Player.class);
        return Mono.just(DefaultPayload.create(playerTransform.transformDataToByteArray(updated)));
    }

    private PlayerEventRequest playerEventRequestHandler(PlayerEventRequest playerEventRequest) {
        if (playerEventRequest.getEventType() == EventType.LVL_UP) {
            Hero hero = playerEventRequest.getPlayer().getHero();
            hero.setLevel(hero.getLevel() + 1);
        } else if (playerEventRequest.getEventType() == EventType.ADDING_BUFF) {
            Attribute attribute = playerEventRequest.getPlayer().getHero().getAttribute();
            Integer attackSpeed = attribute.getAttackSpeed() + 50;
            attribute.withAttackSpeed(attackSpeed);
        }
        return playerEventRequest;
    }

    @Override
    public Mono<Void> fireAndForget(Payload payload) {
        return super.fireAndForget(payload);
    }

    @Override
    public Flux<Payload> requestStream(Payload payload) {
        return super.requestStream(payload);
    }

    @Override
    public Flux<Payload> requestChannel(Publisher<Payload> payloads) {
        return super.requestChannel(payloads);
    }
}
