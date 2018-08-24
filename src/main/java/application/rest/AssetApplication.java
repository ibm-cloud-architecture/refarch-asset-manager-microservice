package application.rest;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import utils.KeySpaceCreation;
import utils.TableCreation;

@ApplicationPath("/")
@Singleton
@Startup
public class AssetApplication extends Application {
	
	@PostConstruct
    private void init() {
		
		final KeySpaceCreation keyspace = new KeySpaceCreation();
        keyspace.createKeySpace();
        System.out.println("Keyspace created");
        
        final TableCreation table = new TableCreation();
        table.createTable();
        System.out.println("Tables created");
    }
	
}
