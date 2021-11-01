package org.dzmitry.common.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Skill {

    private String name;
    private String description;
    private SkillType type;

    public enum SkillType {
        DAMAGE, BUFF
    }

}
