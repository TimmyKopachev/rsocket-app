package org.dzmitry.rsocket.server;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dzmitry.common.model.Player;
import org.dzmitry.common.model.Race;
import org.dzmitry.common.request.ChangePlayerNameRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.EnumMap;
import java.util.Map;

import static org.dzmitry.common.DummyDataUtils.createDummyDarkElfPlayer;
import static org.dzmitry.common.DummyDataUtils.createDummyElfPlayer;
import static org.dzmitry.common.DummyDataUtils.createDummyHumanPlayer;

@Slf4j
@Configuration
public class GameRSocketServer {

    @Slf4j
    @Controller
    @AllArgsConstructor
    @DependsOn("dummyPlayerMap")
    static class PlayerController {

        final DummyPlayerMap dummyPlayerMap;

        @MessageMapping("requestResponse")
        Mono<Player> requestResponse(Mono<Race> raceMono) {
            return raceMono.map(dummyPlayerMap::getPlayerByRace);
        }

        @MessageMapping("fireAndForget")
        Mono<Void> fireAndForget(Mono<ChangePlayerNameRequest> changePlayerNameRequest) {
            changePlayerNameRequest
                    .subscribe(request -> log.info("Name is changed from {}} to {}", request.getName(), request.getChangingName()));
            return Mono.empty();
        }

        @MessageMapping("requestStream")
        Flux<Player> requestStream(Mono<Race> raceMono) {
            return raceMono.map(dummyPlayerMap::getPlayerByRace).flux();
        }
        
        @MessageMapping("channel")
        Flux<Player> channel(Flux<Integer> serialNumber) {
            return serialNumber.map(dummyPlayerMap::getBySerialNumber);
        }

    }
}

@Component
class DummyPlayerMap {

    public Map<Race, Player> dummyPlayerMap = new EnumMap<>(Race.class);

    @PostConstruct
    void setup() {
        dummyPlayerMap.put(Race.ELF, createDummyElfPlayer());
        dummyPlayerMap.put(Race.DARK_ELF, createDummyDarkElfPlayer());
        dummyPlayerMap.put(Race.HUMAN, createDummyHumanPlayer());
    }

    public Player getPlayerByRace(Race race) {
        return dummyPlayerMap.get(race);
    }

    public Player getBySerialNumber(Integer serialNumber) {
        Race race = (Race) dummyPlayerMap.keySet().toArray()[serialNumber];
        return dummyPlayerMap.get(race);
    }

}
