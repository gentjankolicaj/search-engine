package org.search.engine.relational.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor

public class DocumentToken {

    // @Column(name = "token_value")
    private String tokenValue;


    //  @Column(name = "document_id")
    private Long documentId;


    // @Column(name = "token_index")
    private Long tokenIndex;

}
