package org.search.engine.relational.domain;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Document implements Serializable {

  //@Column(name="id")
  private Integer docId;


}
