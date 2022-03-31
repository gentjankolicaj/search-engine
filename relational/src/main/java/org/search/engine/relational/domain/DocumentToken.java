package org.search.engine.relational.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocumentToken implements Serializable {

    // @Column(name = "token_value")
    private String tokenValue;

    //  @Column(name = "document_id")
    private Long documentId;

    // @Column(name = "document_id")
    private Integer tokenIndex;


}
