package org.search.engine.console.result;

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

  // @Column(name = "document_id")
  private Integer tokenIndex;


}