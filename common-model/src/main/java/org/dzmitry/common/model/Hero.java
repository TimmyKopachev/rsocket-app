package org.dzmitry.common.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Hero {

    @With
    private Integer level;
    private Attribute attribute;
    private List<Skill> skills;

}
