package utils;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.ResultSetFuture;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.google.common.collect.Lists;
import com.google.common.util.concurrent.ListenableFuture;

import model.AssetEvent;

public class AssetEventDAOImpl implements AssetEventDAO {
	
	private final Session session;

	private final String cassandra_keyspace = "assetmonitoring";
	private final String cassandra_table_event = "event_metrics";
	
	public AssetEventDAOImpl(Session session){
	   this.session=session;
	}
	
	@Override
	public List<AssetEvent> getAssetEvents() {
		
		List<AssetEvent> assetEvents = new ArrayList<>();
		ResultSet resultset = null;
		String[] list = {"id"};
		List<ResultSetFuture> futures = Lists.newArrayListWithExpectedSize(list.length);
		futures.add(session.executeAsync("SELECT * FROM "+cassandra_keyspace+ "."+cassandra_table_event));
		
		for (ListenableFuture<ResultSet> future : futures){
			try {
				resultset = future.get();
			} 
			catch (InterruptedException | ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		for (Row row : resultset) {
			AssetEvent assetEvent = new AssetEvent();
			assetEvent.setId(row.getString("id"));
			assetEvent.setCurrent(row.getDouble("current"));
			assetEvent.setPressure(row.getInt("pressure"));
			assetEvent.setRotation(row.getInt("rotation"));
			assetEvent.setTemperature(row.getInt("temperature"));
			assetEvent.setTs(row.getTimestamp("timecreated"));
			assetEvents.add(assetEvent);
	       }
	       
	       return assetEvents;
	}

	@Override
	public List<AssetEvent> getEventHistory(Timestamp fromDate, Timestamp toDate) {
		
		List<AssetEvent> assetEvents = new ArrayList<>();
		ResultSet resultset = null;
		
		String[] list = {"id","timecreated"};
		
		List<ResultSetFuture> futures = Lists.newArrayListWithExpectedSize(list.length);
		
		futures.add(session.executeAsync("SELECT * FROM "+cassandra_keyspace+ "."+cassandra_table_event+" WHERE timecreated >= '"+fromDate+"' and timecreated <= '"+toDate+"' ALLOW FILTERING" ));
		
		for (ListenableFuture<ResultSet> future : futures){
			try {
				resultset = future.get();
			} 
			catch (InterruptedException | ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				}
		}
		
		for (Row row : resultset) {
			AssetEvent assetEvent = new AssetEvent();
			assetEvent.setId(row.getString("id"));
			assetEvent.setCurrent(row.getDouble("current"));
			assetEvent.setPressure(row.getInt("pressure"));
			assetEvent.setRotation(row.getInt("rotation"));
			assetEvent.setTemperature(row.getInt("temperature"));
			assetEvent.setTs(row.getTimestamp("timecreated"));
			assetEvents.add(assetEvent);
	       }
	       
	       return assetEvents;
	}

	@Override
	public Response saveEvent(AssetEvent assetEvent) {
		session.executeAsync("insert into "+cassandra_keyspace+ "."+cassandra_table_event
				+ "(id, current, rotation, pressure, temperature, timecreated) "
				+ "values ('"+assetEvent.getId()+"', "+assetEvent.getCurrent()+", "+assetEvent.getRotation()+", "+assetEvent.getPressure()+", "
				+ ""+assetEvent.getTemperature()+", toTimestamp(now()));");
		
		return Response.ok(assetEvent, MediaType.APPLICATION_JSON).build();
	}

}
