package utils;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Host;
import com.datastax.driver.core.Metadata;
import com.datastax.driver.core.Session;


public class CassandraConnection {
	
	Logger logger = LoggerFactory.getLogger(CassandraConnection.class);
	
	private Cluster cluster;
	
	private Session session;
	
	private Config config;
	
	private String cassandra_host; 
	
	private int port;
	
	public void getConnection(){
		config = ConfigProvider.getConfig();
		cassandra_host = config.getValue("cassandra_host", String.class);
		port = config.getValue("cassandra_port", Integer.class);
		logger.info("Acquired host "+cassandra_host+" with port "+port);
		cluster = Cluster.builder().addContactPoint(cassandra_host).withPort(port).build();
		final Metadata metdata = cluster.getMetadata();
		logger.info("Connected to the cluster: %s\n", metdata.getClusterName());
		for (final Host host : metdata.getAllHosts()){
			logger.info("Cassandra Datacenter: %s; Cassandra Host: %s; Cassandra Rack: %s\n",
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
