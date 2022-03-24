package org.search.engine.console.result;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
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
