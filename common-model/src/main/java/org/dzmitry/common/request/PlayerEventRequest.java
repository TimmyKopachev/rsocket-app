package org.dzmitry.common.request;

import lombok.Data;
import org.dzmitry.common.model.Player;
import org.dzmitry.common.model.event.EventType;

@Data
public class PlayerEventRequest {

    private Player player;
    private EventType eventType;
}
