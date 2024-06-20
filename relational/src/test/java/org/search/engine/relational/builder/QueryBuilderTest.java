package org.search.engine.relational.builder;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.search.engine.relational.builder.query.QueryBuilder;
import org.search.engine.relational.builder.query.QuerySql;

class QueryBuilderTest {

  static QueryBuilder builder;

  @BeforeAll
  static void setup() {
    builder = new QueryBuilder();
  }

  @AfterAll
  static void tear() {
  }

  @BeforeEach
  void setUp() {
  }

  @AfterEach
  void tearDown() {
  }

  @Test
  void build() {
    String table = "document_token";
    String column = "token_value";

    List<String> input0 = Arrays.asList("query", "butter");
    List<String> input1 = Arrays.asList("query", "(", "butter", "|", "milk", ")");
    List<String> input2 = Arrays.asList("query", "(", "butter", "|", "potato", ")", "&", "salt");
    List<String> input3 = Arrays.asList("query", "(", "butter", "|", "potato", ")", "&", "(",
        "salt", "|", "sugar", ")");

    QuerySql<String> querySql0 = builder
        .setTable(table)
        .setColumn(column)
        .setParams(input0)
        .build();
    String query0 = querySql0.getQuery();
    assertEquals("select * from " + table + " where token_value=? ", query0);

    QuerySql<String> querySql1 = builder
        .setTable(table)
        .setColumn(column)
        .setParams(input1)
        .build();
    String query1 = querySql1.getQuery();
    assertEquals("select * from " + table + " where ( " + column + "=? or " + column + "=? ) ",
        query1);

    QuerySql<String> querySql2 = builder
        .setTable(table)
        .setColumn(column)
        .setParams(input2)
        .build();
    String query2 = querySql2.getQuery();
    assertEquals(
        "select * from " + table + " where ( " + column + "=? or " + column + "=? ) and " + column
            + "=? ", query2);

    QuerySql<String> querySql3 = builder
        .setTable(table)
        .setColumn(column)
        .setParams(input3)
        .build();
    String query3 = querySql3.getQuery();
    assertEquals(
        "select * from " + table + " where ( " + column + "=? or " + column + "=? ) and ( " + column
            + "=? or " + column + "=? ) ", query3);

  }


  @Test
  void buildSql() {
    String table = "document_token";
    String column = "token_value";

    List<String> input0 = Arrays.asList("query", "butter");
    List<String> input1 = Arrays.asList("query", "(", "butter", "|", "milk", ")");
    List<String> input2 = Arrays.asList("query", "(", "butter", "|", "potato", ")", "&", "salt");
    List<String> input3 = Arrays.asList("query", "(", "butter", "|", "potato", ")", "&", "(",
        "salt", "|", "sugar", ")");

    String query0 = builder
        .setTable(table)
        .setColumn(column)
        .setParams(input0)
        .buildSql();
    assertEquals("select * from " + table + " where token_value='butter' ", query0);

    String query1 = builder
        .setTable(table)
        .setColumn(column)
        .setParams(input1)
        .buildSql();
    assertEquals(
        "select * from " + table + " where ( " + column + "='butter' or " + column + "='milk' ) ",
        query1);

    String query2 = builder
        .setTable(table)
        .setColumn(column)
        .setParams(input2)
        .buildSql();
    assertEquals("select * from " + table + " where ( " + column + "='butter' or " + column
        + "='potato' ) and " + column + "='salt' ", query2);

    String query3 = builder
        .setTable(table)
        .setColumn(column)
        .setParams(input3)
        .buildSql();
    assertEquals("select * from " + table + " where ( " + column + "='butter' or " + column
        + "='potato' ) and ( " + column + "='salt' or " + column + "='sugar' ) ", query3);

  }
}