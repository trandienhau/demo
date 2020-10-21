package com.example.demo.config.mongoconfig;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

@SuppressWarnings("deprecation")
public abstract class AbstractMongoConfig {
	String MONGODB_CONNECTION_PATTERN = "mongodb://%s:%s/%s";
    @Value("${mongodb.host}")
    private String host;
    @Value("${mongodb.port}")
    private int port;
    @Value("${mongodb.username}")
    private String username;
    @Value("${mongodb.password}")
    private String password;
    @Value("${mongodb.authentication-database}")
    private String authenticationDatabase;

    public MongoDatabaseFactory mongoDbFactory(String database) {
        return new SimpleMongoClientDatabaseFactory(getMongoClient(), database);
    }

    private MongoClient getMongoClient() {
        try {
            //String connectionStr = "mongodb://localhost:27017/admin";
        	String connectionStr = String.format(MONGODB_CONNECTION_PATTERN, host, port , authenticationDatabase);
        	System.out.println("connectionStr: " + connectionStr);
            return MongoClients.create(connectionStr);
        } catch (Exception e) {
            return null;
        }
    }

    public abstract MongoTemplate getMongoTemplate() throws Exception;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAuthenticationDatabase() {
        return authenticationDatabase;
    }

    public void setAuthenticationDatabase(String authenticationDatabase) {
        this.authenticationDatabase = authenticationDatabase;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
