package org.dzmitry.common.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Attribute {

    private Integer strength;
    private Integer agility;

    private Integer moveSpeed;
    @With
    private Integer attackSpeed;

}
