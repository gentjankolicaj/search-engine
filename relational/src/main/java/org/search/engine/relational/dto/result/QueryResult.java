package org.search.engine.relational.dto.result;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class QueryResult<O> implements Result<O> {
    private Integer type;
    private List<O> results;


    @Override
    public Integer getType() {
        return type;
    }

    @Override
    public List<O> getResults() {
        return results;
    }
}
