package utils;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.datastax.driver.core.Session;

public class KeySpaceCreation {
	
	Logger logger = LoggerFactory.getLogger(KeySpaceCreation.class);
	
	private Config config = ConfigProvider.getConfig();
	private final String cassandra_keyspace = config.getValue("cassandra_keyspace", String.class);
	
	CassandraConnection cc = new CassandraConnection();

	public void createKeySpace(){
		
		cc.getConnection();
		
		//Creating Session object
	    Session session = cc.getSession();

	    String query = "CREATE KEYSPACE IF NOT EXISTS " +cassandra_keyspace+ " WITH replication " + "= {'class':'NetworkTopologyStrategy', 'DC1':1};";

	    //Executing the query
	    session.execute(query);
	    logger.info("Keyspace "+cassandra_keyspace+" got created");
	      
	    cc.close();
	}

}