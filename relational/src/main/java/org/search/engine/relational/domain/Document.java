package org.search.engine.relational.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Document implements Serializable {

    //@Column(name="id")
    private Integer docId;


}
