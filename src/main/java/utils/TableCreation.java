package utils;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.datastax.driver.core.Session;

public class TableCreation {
	
	Logger logger = LoggerFactory.getLogger(KeySpaceCreation.class);
	
	private Config config = ConfigProvider.getConfig();
	private final String cassandra_keyspace = config.getValue("cassandra_keyspace", String.class);
	private final String cassandra_table = config.getValue("cassandra_table", String.class);
	CassandraConnection cc = new CassandraConnection();
	
	public void createTable(){
		
		cc.getConnection();
		
		//Getting Session object
	    Session session = cc.getSession();
		
		final String createAssetCql = "create TABLE IF NOT EXISTS "+cassandra_keyspace+ "."+cassandra_table+"(id text PRIMARY KEY, os text, type text, ipaddress text, "
				+ "version text, antivirus text, current double, rotation int, pressure int, temperature int, latitude double, longitude double)";

	    final String createIndexType = "CREATE INDEX IF NOT EXISTS ON "+cassandra_keyspace+ "."+cassandra_table+"(type)";
	    
	    //Create table
	    session.execute(createAssetCql);
	    logger.info("Table "+cassandra_table);
	    
	    //Create indexes
	    session.execute(createIndexType);
	    logger.info("Index on type");
	    
	    cc.close();
	}

}
