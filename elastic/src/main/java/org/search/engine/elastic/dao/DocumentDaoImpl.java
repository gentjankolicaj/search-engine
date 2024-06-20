package org.search.engine.elastic.dao;

import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.search.engine.elastic.domain.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.NoSuchIndexException;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DocumentDaoImpl implements DocumentDao {

  private static final String DOCUMENT_INDEX = "search_data";
  private static final String SEARCH_FIELD = "tokens";

  private final ElasticsearchOperations elasticsearchOperations;

  @Autowired
  public DocumentDaoImpl(ElasticsearchOperations elasticsearchOperations) {
    this.elasticsearchOperations = elasticsearchOperations;
  }

  @Override
  public void update(Document document) {
    try {
      elasticsearchOperations.delete("" + document.getDocId(), Document.class);
    } catch (NoSuchIndexException nsie) {
      log.error(nsie.getMessage());
    }
    elasticsearchOperations.save(document, IndexCoordinates.of(DOCUMENT_INDEX));
  }

  public List<Document> readAll(String query) {
    QueryBuilder matchSpecificFieldQuery = QueryBuilders
        .queryStringQuery(query)
        .field(SEARCH_FIELD);

    Query searchQuery = new NativeSearchQueryBuilder()
        .withQuery(matchSpecificFieldQuery)
        .build();

    SearchHits<Document> documentSearchHits = elasticsearchOperations.search(searchQuery,
        Document.class, IndexCoordinates.of(DOCUMENT_INDEX));
    return documentSearchHits.stream().map(sh -> sh.getContent()).collect(Collectors.toList());
  }
}
