package utils;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;

public class KeySpaceCreation {

	public void createKeySpace(){

//		  Config config = ConfigProvider.getConfig();
//		  final String cassandra_keyspace = config.getValue("cassandra_keyspace", String.class);
//		  final String cassandra_host = config.getValue("cassandra_host", String.class);
//		  final int cassandra_port = config.getValue("cassandra_port", Integer.class);

		  final String cassandra_keyspace = "assetmonitoring";
		  final String cassandra_host = "localhost";
		  final int cassandra_port = 9042;

	      String query = "CREATE KEYSPACE IF NOT EXISTS " +cassandra_keyspace+ " WITH replication " + "= {'class':'SimpleStrategy', 'replication_factor':1};";

	      //creating Cluster object
	      Cluster cluster = Cluster.builder().addContactPoint(cassandra_host).withPort(cassandra_port).build();

	      //Creating Session object
	      Session session = cluster.connect();

	      //Executing the query
	      session.execute(query);

	      //using the KeySpace
	      session.execute("USE "+cassandra_keyspace);
	}

}
