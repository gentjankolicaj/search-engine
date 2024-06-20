package org.search.engine.elastic.domain;

import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@org.springframework.data.elasticsearch.annotations.Document(indexName = "search_data")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Document implements Serializable {

  @Id
  @Field(type = FieldType.Long, name = "doc_id")
  private Long docId;

  @Field(type = FieldType.Text, name = "tokens")
  private List<String> tokens;


}