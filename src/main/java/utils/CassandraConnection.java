package utils;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Host;
import com.datastax.driver.core.Metadata;
import com.datastax.driver.core.Session;

public class CassandraConnection {
	
	   private Cluster cluster;
	  
	   private Session session;
	   
	   private Config config = ConfigProvider.getConfig();
	   
	   private String cassandra_host = config.getValue("cassandra_host", String.class);
	   private int port = config.getValue("cassandra_port", Integer.class);
	   private String cassandra_keyspace = config.getValue("cassandra_keyspace", String.class);
	   private String cassandra_table = config.getValue("cassandra_table", String.class);
		  
	
	   
	   public void getConfig(){
		 
		  cluster = Cluster.builder().addContactPoint(cassandra_host).withPort(port).build();
	      final Metadata metdata = cluster.getMetadata();
	      System.out.printf("Connected to the cluster: %s\n", metdata.getClusterName());
	      for (final Host host : metdata.getAllHosts())
	      {
	            System.out.printf("Cassandra Datacenter: %s; Cassandra Host: %s; Cassandra Rack: %s\n",
	            host.getDatacenter(), host.getAddress(), host.getRack());
	      }
	      session = cluster.connect();
	   }
	   
	   public void getConnection(){
			final String createAssetCql = "create TABLE IF NOT EXISTS "+cassandra_keyspace+ "."+cassandra_table+"(id text PRIMARY KEY, os text, type text, ipaddress text, "
					+ "version text, antivirus text, current double, rotation int, pressure int, temperature int, latitude double, longitude double)";
			final String createIndexOS = "CREATE INDEX IF NOT EXISTS ON assetmonitoring.assets (os)";
			
		    final KeySpaceCreation keyspace = new KeySpaceCreation();
		    final String createIndexType = "CREATE INDEX IF NOT EXISTS ON assetmonitoring.assets (type)";
		    
		    //Get the cassandra config details
		    getConfig();
		    
		    //Create keyspace
		    keyspace.createKeySpace();
			
		    //Create table
	    	getSession().execute(createAssetCql);
	    	System.out.println("Table created");
	    	
	    	//Create indexes
	    	getSession().execute(createIndexOS);
	    	getSession().execute(createIndexType);
	    	System.out.println("Indexes created");
		}
	   
	   public Session getSession()
	   {
	      return this.session;
	   }
	   
	   public void close()
	   {
	      cluster.close();
	   }

}
