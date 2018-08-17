package utils;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;

public class TableCreation {
	
	private final String cassandra_keyspace = "assetmonitoring";
	private final String cassandra_table = "assets";
	private final String cassandra_host = "localhost";
	private final int cassandra_port = 9042;
	
	public void createTable(){
		
		final String createAssetCql = "create TABLE IF NOT EXISTS "+cassandra_keyspace+ "."+cassandra_table+"(id text PRIMARY KEY, os text, type text, ipaddress text, "
				+ "version text, antivirus text, current double, rotation int, pressure int, temperature int, latitude double, longitude double)";

	    final String createIndexType = "CREATE INDEX IF NOT EXISTS ON "+cassandra_keyspace+ "."+cassandra_table+"(type)";
	    
	    //creating Cluster object
	    Cluster cluster = Cluster.builder().addContactPoint(cassandra_host).withPort(cassandra_port).build();
	    
	    //Creating Session object
	    Session session = cluster.connect();
	    
	    //Create table
	    session.execute(createAssetCql);
	    System.out.println("Table created");
	    
	    //Create indexes
	    session.execute(createIndexType);
	    System.out.println("Indexes created");
	    
	    cluster.close();
	}

}
