package org.search.engine.elastic.dto.document;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DocumentDto {

    private Long docId;
    private List<String> tokens;


}
