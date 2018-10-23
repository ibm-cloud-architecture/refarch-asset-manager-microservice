package utils;

import org.cassandraunit.dataset.cql.ClassPathCQLDataSet;
import org.junit.AfterClass;
import org.junit.BeforeClass;



public class AbstractTest {

	protected static CassandraEmbedded cassandra = new CassandraEmbedded(new ClassPathCQLDataSet("db_setup.cql", "assetmonitoring"));

	@BeforeClass
	public static void tearUp() throws Exception {
	cassandra.start();
	}

	@AfterClass
	public static void tearDown() {
	cassandra.stop();
	}

}
