package org.dzmitry.rsocket.client.request;

import io.rsocket.util.DefaultPayload;
import lombok.RequiredArgsConstructor;
import org.dzmitry.common.model.Attribute;
import org.dzmitry.common.model.Hero;
import org.dzmitry.common.model.Player;
import org.dzmitry.common.model.Skill;
import org.dzmitry.common.model.event.EventType;
import org.dzmitry.common.request.PlayerEventRequest;
import org.dzmitry.common.utils.PayloadTransformation;
import org.dzmitry.rsocket.client.GameRSocketClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.UUID;


@RestController
@RequiredArgsConstructor
public class RequestResponseController {

    private final GameRSocketClient gameRSocketClient;

    private PlayerEventRequest playerEventRequest;

    @PostConstruct
    public void setup() {
        playerEventRequest = createDummyPlayerEventRequest();
    }

    // expected behaviour: player's level has to be increased after each request with type EventType LEVEL_UP
    @RequestMapping("/send")
    public Mono<Player> RequestResponseMessage() {
        PayloadTransformation<PlayerEventRequest> playerEventTransform = new PayloadTransformation<>(PlayerEventRequest.class);

        byte[] requestByteArray = playerEventTransform.transformDataToByteArray(playerEventRequest);

        PayloadTransformation<Player> playerTransform = new PayloadTransformation<>(Player.class);

        gameRSocketClient.getSocket().requestResponse(DefaultPayload.create(requestByteArray))
                .subscribe(payload -> playerEventRequest.setPlayer(playerTransform.transformByteArrayToData(payload.getData().array())));
        // alternative to custom object serialization:
        // DefaultPayload.create(payload).getDataUtf8()
        return Mono.just(playerEventRequest.getPlayer());
    }

    PlayerEventRequest createDummyPlayerEventRequest() {
        PlayerEventRequest request = new PlayerEventRequest();
        request.setEventType(EventType.LVL_UP);

        Player player = new Player(UUID.randomUUID().toString(), "Dzmitry_Kapachou");

        Hero hero = new Hero();
        hero.setLevel(7);
        hero.setSkills(List.of(
                new Skill("move", "increase movespeed", Skill.SkillType.BUFF),
                new Skill("power shot", "the shot hits with criticals attack", Skill.SkillType.DAMAGE)
        ));
        hero.setAttribute(new Attribute(10, 10, 200, 50));
        player.setHero(hero);

        request.setPlayer(player);
        return request;
    }

}
