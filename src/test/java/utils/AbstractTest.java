package utils;

import java.util.List;

import org.cassandraunit.dataset.cql.ClassPathCQLDataSet;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Statement;
import com.datastax.driver.core.querybuilder.Clause;
import com.datastax.driver.core.querybuilder.QueryBuilder;

import static org.junit.Assert.assertEquals;

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

	protected Row getAssetsById(String id) {
    
	Clause clause = QueryBuilder.eq("id", id);
	Statement select = QueryBuilder
	      .select().all()
	      .from("assetmonitoring.assets")
	      .where(clause);
	
	ResultSet resultSet = cassandra.session.execute(select);
	
	List<Row> rows = resultSet.all();
	assertEquals(rows.size(), 1);
	return rows.get(0);
	}



}
