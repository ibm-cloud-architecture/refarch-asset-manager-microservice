package application.rest;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.datastax.driver.core.Session;

import model.AssetEvent;
import utils.AssetEventDAOImpl;
import utils.CassandraConnection;

public class AssetEventService {
	
    CassandraConnection cc  = new CassandraConnection();
    
	public AssetEventService(CassandraConnection cc){
		this.cc = cc;
	}
	
	public List<AssetEvent> getAssetEvents(){

		List<AssetEvent> assetEvents = new ArrayList<>();

	    cc.getConnection();
	    Session session = cc.getSession();

	    AssetEventDAOImpl assetEventDAO = new AssetEventDAOImpl(session);

	    assetEvents = assetEventDAO.getAssetEvents();
	    
	    cc.close();
	    return assetEvents;
	}
	
	public List<AssetEvent> getEventHistory(Timestamp fromDate, Timestamp toDate){

		List<AssetEvent> assetEvents = new ArrayList<>();

	    cc.getConnection();
	    Session session = cc.getSession();

	    AssetEventDAOImpl assetEventDAO = new AssetEventDAOImpl(session);

	    assetEvents = assetEventDAO.getEventHistory(fromDate, toDate);
	    
	    cc.close();
	    return assetEvents;
	}
	
	public Response saveEvent(AssetEvent assetEvent) {
		
		cc.getConnection();
	    Session session = cc.getSession();

	    AssetEventDAOImpl assetEventDAO = new AssetEventDAOImpl(session);

	    assetEventDAO.saveEvent(assetEvent);
	    
	    cc.close();
	    
	    return Response.ok(assetEvent, MediaType.APPLICATION_JSON).build(); 
		
	}
	
}
