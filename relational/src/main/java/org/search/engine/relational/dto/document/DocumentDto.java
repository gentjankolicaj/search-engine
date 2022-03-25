package org.search.engine.relational.dto.document;


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
