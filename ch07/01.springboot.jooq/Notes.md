
# Working with JOOQ

JOOQ (Java Object Oriented Querying) is a persistence framework that embraces SQL. JOOQ provides the following features:

• Typesafe SQL using DSL API
• Typesafe database object referencing using code generation 
• Easy-to-use API for querying and data fetching
• SQL logging and debugging

# Introduction to JOOQ

JOOQ is a Java persistence framework that provides typesafe QueryDSL so that you can implement the persistence layer of your application in a typesafe manner. JOOQ provides code generation tools to generate code based on the database schema and you can use that generated code to build typesafe queries.

# Using Spring Boot's JOOQ Starter
Spring Boot provides a starter , called spring-boot-starter-jooq which allows you to quickly integrate with JOOQ.
This section shows you how to use spring-boot-stareter-jooq using a step-by-step approach.

# Configure Spring Boot JOOQ Starter

Create a Spring Boot Maven-based project and add the spring-boot-starter-jooq dependency along with H2 and MySQL driver dependencies.

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-jooq</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
    <scope>test</scope>
</dependency>
<dependency>
    <groupId>com.h2database</groupId>
    <artifactId>h2</artifactId>
</dependency>
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
</dependency>


# Code Generation Using the JOOQ Maven Codegen Plugin

JOOQ provides the JOOQ Maven Codegen plugin to generate database artifacts using Maven goals. In this section, you see how to use Maven profiles to configure the jooq-codegen-maven configuration properties based on database type.

# Add JOOQ Generated Code as a Source Folder

Next, you have to configure build-helper-maven-plugin so that Maven will add the JOOQ generated code
to the gensrc/main/java directory as a source folder.

# Using JOOQ DSL
DSLContext is the main entry point for the JOOQ DSL API. 
