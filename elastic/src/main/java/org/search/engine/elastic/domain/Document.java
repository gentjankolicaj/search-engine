package org.search.engine.elastic.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;

@org.springframework.data.elasticsearch.annotations.Document(indexName = "search_data")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Document {

    @Id
    @Field(type = FieldType.Long, name = "doc_id")
    private Long docId;

    @Field(type = FieldType.Text, name = "tokens")
    private List<String> tokens;



}