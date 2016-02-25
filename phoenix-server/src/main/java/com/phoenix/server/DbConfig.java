/**
 * Copyright 2016 Nigel Schuster. MongoDB/Database configuration
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
 * Provides MongoDB access.
 *
 * @author nschuste
 * @version 1.0.0
 * @since Feb 14, 2016
 */
@Configuration
public class DbConfig {
  /**
   * ConnectionFactory
   *
   * @author nschuste
   * @version 1.0.0
   * @return
   * @throws UnknownHostException
   * @since Feb 24, 2016
   */
  @Bean(name = "mongoFactory")
  public MongoDbFactory mongo() throws UnknownHostException {
    SimpleMongoDbFactory fact =
        new SimpleMongoDbFactory(new MongoClientURI("mongodb://" + System.getenv("DATABASE")
            + ":27017/" + System.getProperty("database")));
    return fact;
  }

  /**
   * Mongo Client, if used with camel (currently not in use
   * 
   * @author nschuste
   * @version 1.0.0
   * @return
   * @throws DataAccessException
   * @throws UnknownHostException
   * @since Feb 24, 2016
   */
  @Bean(name = "mongo")
  public Mongo mongoDb() throws DataAccessException, UnknownHostException {
    return this.mongo().getDb().getMongo();
  }

  /**
   * Needed by Spring
   * 
   * @author nschuste
   * @version 1.0.0
   * @return
   * @throws UnknownHostException
   * @since Feb 24, 2016
   */
  @Bean(name = "mongoTemplate")
  public MongoTemplate template() throws UnknownHostException {
    return new MongoTemplate(this.mongo());
  }
}
