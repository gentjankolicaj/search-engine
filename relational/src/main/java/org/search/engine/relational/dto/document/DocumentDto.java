package org.search.engine.relational.dto.document;


import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DocumentDto {

  private Long docId;
  private List<String> tokens;


}
