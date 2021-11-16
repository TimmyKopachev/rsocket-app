package org.dzmitry.common.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChangePlayerNameRequest {

    private String name;
    private String changingName;
}
