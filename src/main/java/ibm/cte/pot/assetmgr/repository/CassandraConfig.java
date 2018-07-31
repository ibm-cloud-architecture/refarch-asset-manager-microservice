package ibm.cte.pot.assetmgr.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractReactiveCassandraConfiguration;
import org.springframework.data.cassandra.config.CassandraClusterFactoryBean;
import org.springframework.data.cassandra.config.CassandraSessionFactoryBean;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.core.convert.CassandraConverter;
import org.springframework.data.cassandra.core.convert.MappingCassandraConverter;
import org.springframework.data.cassandra.core.cql.keyspace.CreateKeyspaceSpecification;
import org.springframework.data.cassandra.core.cql.keyspace.KeyspaceOption;
import org.springframework.data.cassandra.core.mapping.CassandraMappingContext;
import org.springframework.data.cassandra.repository.config.EnableReactiveCassandraRepositories;

/**
* Cassandra SetUp
*
* @author  Jerome boyer, Hemankita Perabathini
* 
*/
@Configuration
@EnableReactiveCassandraRepositories
public class CassandraConfig extends AbstractReactiveCassandraConfiguration {

    @Value("${cassandra.contactpoints}")
    private String contactPoints;

    @Value("${cassandra.port}")
    private int port;

    @Value("${cassandra.keyspace}")
    private String keySpace;

    @Value("${cassandra.basepackages}")
    private String basePackages;
   

    @Override
    public SchemaAction getSchemaAction() {
        return SchemaAction.CREATE_IF_NOT_EXISTS;
    }

	@Override
	protected String getKeyspaceName() {
		return keySpace;
	}
	
	@Override
	public String[] getEntityBasePackages() {
	    return new String[]{"ibm.cte.pot.assetmgr.model"};
	}

   @Bean
   @Override
    public CassandraClusterFactoryBean cluster() {
        CassandraClusterFactoryBean cluster = 
          new CassandraClusterFactoryBean();
        cluster.setContactPoints(this.contactPoints);
        cluster.setPort(this.port);
        cluster.setKeyspaceCreations(getKeyspaceCreations());
        return cluster;
    }
   
   @Bean
   public CassandraConverter converter() throws ClassNotFoundException {
       return new MappingCassandraConverter(mappingContext());
   }
   
   @Bean
   public CassandraMappingContext mappingContext() throws ClassNotFoundException {
       CassandraMappingContext mappingContext= new CassandraMappingContext();
       mappingContext.setInitialEntitySet(getInitialEntitySet());
       return mappingContext;
   }
   
   @Bean
   public CassandraSessionFactoryBean session() {
       CassandraSessionFactoryBean cassandraSessionFactoryBean = new CassandraSessionFactoryBean();
       cassandraSessionFactoryBean.setCluster(cluster().getObject());
       cassandraSessionFactoryBean.setKeyspaceName(getKeyspaceName());
       try {
		cassandraSessionFactoryBean.setConverter(converter());
       } catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
       }
       cassandraSessionFactoryBean.setSchemaAction(SchemaAction.CREATE_IF_NOT_EXISTS);
       return cassandraSessionFactoryBean;
   }
   
   protected List<CreateKeyspaceSpecification> getKeyspaceCreations() {
       List<CreateKeyspaceSpecification> createKeyspaceSpecifications = new ArrayList<>();
       createKeyspaceSpecifications.add(getKeySpaceSpecification());
       return createKeyspaceSpecifications;
   }

   // Creates Keyspace if it doesn't exist
   private CreateKeyspaceSpecification getKeySpaceSpecification() {
	   CreateKeyspaceSpecification specification = CreateKeyspaceSpecification.createKeyspace(keySpace)
    			  .ifNotExists(true)
    		      .with(KeyspaceOption.DURABLE_WRITES, true)
    		      .withSimpleReplication();
	   return specification;
   }
 	   

}