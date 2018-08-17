package utils;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Host;
import com.datastax.driver.core.Metadata;
import com.datastax.driver.core.Session;

public class CassandraConnection {

	   private Cluster cluster;

	   private Session session;

//	   private Config config = ConfigProvider.getConfig();
//
//	   private String cassandra_host = config.getValue("cassandra_host", String.class);
//	   private int port = config.getValue("cassandra_port", Integer.class);
//	   private String cassandra_keyspace = config.getValue("cassandra_keyspace", String.class);
//	   private String cassandra_table = config.getValue("cassandra_table", String.class);

	   private String cassandra_host = "localhost";
	   private int port = 9042;
	   

	   public void getConnection(){
		   
		   cluster = Cluster.builder().addContactPoint(cassandra_host).withPort(port).build();
		   final Metadata metdata = cluster.getMetadata();
		   System.out.printf("Connected to the cluster: %s\n", metdata.getClusterName());
		   for (final Host host : metdata.getAllHosts()){
			   System.out.printf("Cassandra Datacenter: %s; Cassandra Host: %s; Cassandra Rack: %s\n",
					   host.getDatacenter(), host.getAddress(), host.getRack());
			
		   }
		   session = cluster.connect();
		   
	   }
	   
	   public Session getSession(){
		   return this.session;   
	   }
	   
	   public void close(){
		   cluster.close();
	   }

}
