package org.search.engine.relational.builder;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.search.engine.relational.builder.index.IndexBuilder;
import org.search.engine.relational.builder.index.IndexSql;

class IndexBuilderTest {

  static IndexBuilder builder;

  @BeforeAll
  static void setup() {
    builder = new IndexBuilder();
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
  void setParams() {
  }

  @Test
  void setParam() {
  }

  @Test
  void build() {
    List<String> input0 = Arrays.asList("index", "1", "banana");
    List<String> input1 = Arrays.asList("index", "1", "banana", "banana");
    List<String> input2 = Arrays.asList("index", "1", "banana", "apple.orange");

    List<String> input3 = Arrays.asList("index", "1", "banana", "banana", "banana", "banana");

    Map<String, IndexSql> map0 = builder.setParams(input0)
        .build();

    Map<String, IndexSql> map1 = builder.setParams(input1)
        .build();

    Map<String, IndexSql> map2 = builder.setParams(input2)
        .build();

    Map<String, IndexSql> map3 = builder.setParams(input3)
        .build();

    assertTrue(true);

  }
}