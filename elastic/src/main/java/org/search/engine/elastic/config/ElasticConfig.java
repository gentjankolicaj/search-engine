package org.search.engine.elastic.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

//Just in case config,not useful right now
@Configuration
public class ElasticConfig {

  @Value("${spring.elasticsearch.rest.uris}")
  public String uri;

  @Value("${spring.elasticsearch.rest.username}")
  public String username;

  @Value("${spring.elasticsearch.rest.password}")
  public String password;

  @Value("${spring.elasticsearch.rest.connection-timeout}")
  private int connectionTimeout;

  @Value("${spring.elasticsearch.rest.read-timeout}")
  private int readTimeout;


}