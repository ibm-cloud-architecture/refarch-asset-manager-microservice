package application.rest;


import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import utils.KeySpaceCreation;
import utils.TableCreation;

@ApplicationPath("/")
@Singleton
@Startup
public class AssetApplication extends Application {
	
	Logger logger = LoggerFactory.getLogger(AssetApplication.class);

	
	@PostConstruct
    private void init() {
		
		final KeySpaceCreation keyspace = new KeySpaceCreation();
        keyspace.createKeySpace();
        logger.info("Keyspace created");
        
        final TableCreation table = new TableCreation();
        table.createTable();
        logger.info("Tables created");
    }
	
}
