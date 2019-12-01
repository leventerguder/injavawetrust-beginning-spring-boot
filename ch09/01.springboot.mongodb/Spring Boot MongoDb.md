# Introducing MongoDB

MongoDB is an open-source document-oriented NoSQL database.
MongoDB uses a document storage and data interchange format called BSON , which provides a binary representation of JSON-like documents.

MongoDB stores documents in collections.
Collections are anologous to tables in relational databases.


# Installing MongoDB

MongoDB supports a wide variety of platforms, including the Windows, Linux, and MacOS operating systems. To get a complete list of supported platforms, see https://docs.mongodb.com/manual/ installation/#supported-platforms.

You can use the show dbs command to check the list of available databases :

> show dbs

By default , you are connectod to the default database called test. You can use the use _dbname command to switch to another database.

> use users


You can insert a user document into the users collection as follows:

> db.users.insert({"username": "admin","password": "secret" })
If the collection does not exist, it will be created automatically.


You can query the database to return all the documents as follows:

> db.users.find()


You can also apply filters to a query as follows:

> db.users.find({"name": "admin" })

You can also get the count of number of documents as follows:


> db.users.count()


# Introducing Spring Data MongoDB

The Spring Data umbrella project provides the Spring Data Mongo module, which provides support for performing CRUD operations
and dynamic queries based on method names similar to Spring Data JPA

The Spring Data MongoDB module provides the MongoTemplate, which provides higher level abstraction over MongoDB Java driver API com.mongodb.Mongo

If you are not using Spring Boot, you need to configure the MongoDB components using JavaConfig

@Configuration
public class MongoConfiguration
{
  @Bean
  public MongoDbFactory mongoDbFactory() throws Exception {
    return new SimpleMongoDbFactory(new Mongo(), "database");
  }

  @Bean
  public MongoTemplate mongoTemplate() throws Exception {
    return new MongoTemplate(mongoDbFactory());
  }
}

Spring Boot provides the spring-boot-starter-data-mongodb starter to easily work with MongoDB by
autoconfiguring the MongoDB components without requiring the manual configuration.

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-mongodb</artifactId>
</dependency>


By adding the spring-boot-starter-data-mongodb dependency, Spring Boot will autoconfigure MongoClient, MongoDbFactory, MongoTemplate, etc. and connect to the local MongoDB server at mongodb://localhost/test.
You can customize the mongodb server URL by configuring the spring.data.mongodb.uri property in the application.properties file.


spring.data.mongodb.uri=mongodb://remoteserver:27017/blog

You can also use MongoTemplate to save, update, and delete domain objects that internally take care of mapping domain objects to documents in MongoDB.


MongoTemplate, you can also create repositories that extend the MongoRepository interface and provide CRUD operations


You can map a domain object to a particular MongoDB collection name using the @Document annotation.

@Document(collection="users")
public class User
{
    @Id
    private String id;
    private String name;
    private String email;
    private boolean disabled;
    //setters and getters
}



By default, MongoDB generates an ObjectId primary key called _id. But you can map any existing property to be used as the primary key, simply by using the @Id annotation.

public interface UserRepository extends MongoRepository<User, String>
{ 

}


# Using Embedded Mongo for Testing

You can use the following embedded Mongo (https://github.com/flapdoodle-oss/de.flapdoodle. embed.mongo) dependency, which Spring Boot can autoconfigure, so that you can run tests without setting up an actual MongoDB server.

<dependency>
    <groupId>de.flapdoodle.embed</groupId>
    <artifactId>de.flapdoodle.embed.mongo</artifactId>
</dependency>


To learn more about the Spring Data Mongo features, see the Spring Data MongoDB reference documentation at: http://docs.spring.io/spring-data/data-mongo/docs/current/reference/html.