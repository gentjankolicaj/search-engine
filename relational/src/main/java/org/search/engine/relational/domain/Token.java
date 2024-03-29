package org.search.engine.relational.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Token implements Serializable {

    // @Column(name = "value")
    private String value;

}
