package application.rest;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import model.Asset;
import model.AssetEvent;
import utils.CassandraConnection;

@Path("/")
public class AssetEventResource {
	
	CassandraConnection cc = new CassandraConnection();
	AssetEventService assetEventRepo = new AssetEventService(cc);

    @GET
    @Path("/events")
    @Produces(MediaType.APPLICATION_JSON)
    public List<AssetEvent> getAssetEvents(){ 
    	List<AssetEvent> assetEvents = new ArrayList<>();
    	assetEvents = assetEventRepo.getAssetEvents();
    	return assetEvents;
	}
    
    @GET
    @Path("/events/{fromdate}/{todate}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<AssetEvent> getEventHistory(@PathParam("fromdate") String fromDate, @PathParam("todate") String toDate) throws ParseException{ 
    	List<AssetEvent> assetEvents = new ArrayList<>();
		SimpleDateFormat datetimeFormatter1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    	Date lFromDate1 = datetimeFormatter1.parse(fromDate);
    	Date lFromDate2 = datetimeFormatter1.parse(toDate);
    	Timestamp fromdate = new Timestamp(lFromDate1.getTime());
    	Timestamp todate = new Timestamp(lFromDate2.getTime());
    	assetEvents = assetEventRepo.getEventHistory(fromdate, todate);
    	return assetEvents;
	}
    
    @POST
	@Path("/events")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public Response saveEvent(AssetEvent assetEvent) {
    	assetEventRepo.saveEvent(assetEvent);
		try {
			return Response.ok(assetEvent, MediaType.APPLICATION_JSON).build();
		} catch(Exception ex) {
		    return Response.status(500).entity(assetEvent).build();
		}
	}

}
