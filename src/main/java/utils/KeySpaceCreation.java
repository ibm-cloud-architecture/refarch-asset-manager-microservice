package utils;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;

public class KeySpaceCreation {
	
	private Config config = ConfigProvider.getConfig();
	private final String cassandra_keyspace = config.getValue("cassandra_keyspace", String.class);
	private final String cassandra_table = config.getValue("cassandra_table", String.class);
	CassandraConnection cc = new CassandraConnection();

	public void createKeySpace(){
		
		cc.getConnection();
		
		//Creating Session object
	    Session session = cc.getSession();

	    String query = "CREATE KEYSPACE IF NOT EXISTS " +cassandra_keyspace+ " WITH replication " + "= {'class':'NetworkTopologyStrategy', 'DC1':1};";

	    //Executing the query
	    session.execute(query);
	    
	    final String createAssetCql = "create TABLE IF NOT EXISTS "+cassandra_keyspace+ "."+cassandra_table+"(id text PRIMARY KEY, os text, type text, ipaddress text, "
				+ "version text, antivirus text, current double, rotation int, pressure int, temperature int, latitude double, longitude double)";

	    final String createIndexType = "CREATE INDEX IF NOT EXISTS ON "+cassandra_keyspace+ "."+cassandra_table+"(type)";
	    
	    //Create table
	    session.execute(createAssetCql);
	    System.out.println("Table created");
	    
	    //Create indexes
	    session.execute(createIndexType);
	    System.out.println("Indexes created");
	      
	    cc.close();
	}

}