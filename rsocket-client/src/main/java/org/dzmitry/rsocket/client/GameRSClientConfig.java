package org.dzmitry.rsocket.client;

import org.dzmitry.rsocket.client.request.ChannelRSocketClient;
import org.dzmitry.rsocket.client.request.FireForgetRSocketClient;
import org.dzmitry.rsocket.client.request.RequestResponseRSocketClient;
import org.dzmitry.rsocket.client.request.RequestStreamRSocketClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.rsocket.RSocketRequester;

import java.net.URI;

@Configuration
public class GameRSClientConfig {

    @Bean
    RSocketRequester rSocketRequester(RSocketRequester.Builder builder) {
        return builder
                .websocket(URI.create("ws://localhost:9092/"));
        //.tcp("localhost", 9092);
    }

   // @Bean
    RequestResponseRSocketClient requestResponseRSocketClient(RSocketRequester requester) {
        return new RequestResponseRSocketClient(requester);
    }

   //@Bean
    FireForgetRSocketClient fireForgetRSocketClient(RSocketRequester requester) {
        return new FireForgetRSocketClient(requester);
    }

    //@Bean
    RequestStreamRSocketClient requestStreamRSocketClient(RSocketRequester requester) {
        return new RequestStreamRSocketClient(requester);
    }

    @Bean
    ChannelRSocketClient channelRSocketClient(RSocketRequester requester) {
        return new ChannelRSocketClient(requester);
    }


}







