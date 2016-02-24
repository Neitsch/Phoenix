/**
 * Copyright 2016 Nigel Schuster.
 */


package com.phoenix.server;

import java.net.UnknownHostException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.DataAccessException;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import com.mongodb.Mongo;
import com.mongodb.MongoClientURI;

/**
 * @author nschuste
 * @version 1.0.0
 * @since Feb 14, 2016
 */
@Configuration
public class DbConfig {
  @Bean(name = "mongoFactory")
  public MongoDbFactory mongo() throws UnknownHostException {
    SimpleMongoDbFactory fact =
        new SimpleMongoDbFactory(new MongoClientURI("mongodb://" + System.getenv("DATABASE")
            + ":27017/" + System.getProperty("database")));
    return fact;
  }

  @Bean(name = "mongo")
  public Mongo mongoDb() throws DataAccessException, UnknownHostException {
    return this.mongo().getDb().getMongo();
  }

  @Bean(name = "mongoTemplate")
  public MongoTemplate template() throws UnknownHostException {
    return new MongoTemplate(this.mongo());
  }
}
