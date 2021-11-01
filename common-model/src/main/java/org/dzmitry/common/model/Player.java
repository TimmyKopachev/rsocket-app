package org.dzmitry.common.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.With;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Player {

    private String uuid;
    private String name;
    @With
    private Hero hero;

    public Player(String uuid, String name) {
        this.uuid = uuid;
        this.name = name;
    }
}
