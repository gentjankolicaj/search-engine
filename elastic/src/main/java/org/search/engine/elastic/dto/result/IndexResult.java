package org.search.engine.elastic.dto.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IndexResult implements Result<Object> {
    private Integer type;
    private List<Object> results;

    @Override
    public Integer getType() {
        return type;
    }

    @Override
    public List<Object> getResults() {
        return results;
    }
}
