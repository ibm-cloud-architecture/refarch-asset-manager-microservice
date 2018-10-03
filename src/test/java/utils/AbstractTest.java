package utils;

import org.cassandraunit.dataset.cql.ClassPathCQLDataSet;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;


public class AbstractTest {

	protected CassandraEmbedded cassandra = new CassandraEmbedded(new ClassPathCQLDataSet("db_setup.cql", "assetmonitoring"));

	@BeforeClass
	public void tearUp() throws Exception {
	cassandra.start();
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
	cassandra.stop();
	}

}
