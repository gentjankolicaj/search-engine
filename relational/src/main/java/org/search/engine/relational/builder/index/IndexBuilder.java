package org.search.engine.relational.builder.index;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.search.engine.relational.builder.Builder;
import org.springframework.stereotype.Component;

@Component
public class IndexBuilder implements Builder<Map<String, IndexSql>> {

  private List<String> params;


  public IndexBuilder() {
  }


  public IndexBuilder setParams(List<String> params) {
    this.params = params;
    return this;
  }

  public IndexBuilder setParam(String param) {
    this.params.add(param);
    return this;
  }

  //todo: to use StringBuilder instead of +
  @Override
  public Map<String, IndexSql> build() {
    Map<String, IndexSql> map = new HashMap<>();
    int totalParams = params.size();
    String docId = params.get(1);
    List<String> tokenParams = params.subList(2, totalParams);
    int totalTokens = tokenParams.size();

    IndexSql insertDoc = new IndexSql("insert into document (id) values (?)", docId);
    IndexSql deleteDocToken = new IndexSql("delete from document_token where document_id=(?)",
        docId);

    String insertTokenQuery = "insert into token (value) values ";
    List<String> insertTokenQueries = new ArrayList<>();
    List<String> insertTokenParams = new ArrayList<>();
    List<String> alreadyExistingTokens = new ArrayList<>();
    for (int i = 0; i < totalTokens; i++) {
      String s = tokenParams.get(i);
      if (!alreadyExistingTokens.contains(s)) {
        insertTokenQueries.add(insertTokenQuery + "(?);");
        insertTokenParams.add(s);
        alreadyExistingTokens.add(s);
      }
    }
    IndexSql<List<String>> insertToken = new IndexSql<>(insertTokenQueries,
        insertTokenParams.toArray());

    String insertDocTokenQuery = "insert into document_token (token_value,document_id,token_index) values ";
    List<String> alreadyCountedTokens = new ArrayList<>();
    for (int i = 0; i < totalTokens; i++) {
      String s0 = tokenParams.get(i);
      if (!alreadyCountedTokens.contains(s0)) {
        insertDocTokenQuery = insertDocTokenQuery + "(?," + docId + "," + i + "),";
        for (int j = i + 1; j < totalTokens; j++) {
          String s1 = tokenParams.get(j);
          if (s0.equals(s1)) {
            insertDocTokenQuery = insertDocTokenQuery + "(?," + docId + "," + j + "),";
          }
        }
        alreadyCountedTokens.add(s0);
      }
    }
    insertDocTokenQuery = insertDocTokenQuery.substring(0, insertDocTokenQuery.length() - 1) + ";";
    IndexSql insertDocToken = new IndexSql(insertDocTokenQuery, tokenParams.toArray());

    //Add index sql to map
    map.put("insert_document", insertDoc);
    map.put("insert_token", insertToken);
    map.put("delete_document_token", deleteDocToken);
    map.put("insert_document_token", insertDocToken);
    return map;
  }


}


