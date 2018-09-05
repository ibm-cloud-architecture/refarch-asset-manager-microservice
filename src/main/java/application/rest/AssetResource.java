package application.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import model.Asset;
import utils.CassandraConnection;

@Path("/")
public class AssetResource {
	
	CassandraConnection cc = new CassandraConnection();
	AssetService assetRepo = new AssetService(cc);

    @GET
    @Path("/assets")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Asset> getAssets(){ 
    	List<Asset> assets = new ArrayList<>();
    	assets = assetRepo.getAssets();
    	return assets;
	}

	@GET
	@Path("/assets/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Asset> getAssetById(@PathParam("id") String id) {
		List<Asset> assets = new ArrayList<>();
		assets = assetRepo.getAssetById(id);
		return assets;
	}

	@GET
	@Path("assets/type/{type}")
	@Produces(MediaType.APPLICATION_JSON) 
	public List<Asset> getAssetsByType(@PathParam("type") String type) {
		List<Asset> assets = new ArrayList<>();
		assets = assetRepo.getAssetsByType(type);
		return assets;
	}

	@POST
	@Path("/assets")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	public Response createAsset(Asset asset) {
		assetRepo.createAsset(asset);
		try {
			return Response.ok(asset, MediaType.APPLICATION_JSON).build();
		} catch(Exception ex) {
		    return Response.status(500).entity(asset).build();
		}
	}

	@PUT
	@Path("/assets/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateAsset(Asset asset, @PathParam("id") String id) {
		if(id == null || id.trim().length() == 0) {
	        return Response.serverError().entity("id cannot be null").build();
	    }
	    if(asset.getId().equals(id) && !assetRepo.getAssetById(id).isEmpty()) {
	    	assetRepo.updateAsset(asset, id);   
	    }
	    else{
	    	return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("{\"ID\":\"Invalid\"}").build();
	    }
		try {
			return Response.ok(asset, MediaType.APPLICATION_JSON).build();
		} catch(Exception ex) {
		    return Response.status(500).entity(asset).build();
		}
	}

	@DELETE
	@Path("/assets/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response deleteAsset(@PathParam("id") String id) {
		if(id == null || id.trim().length() == 0) {
	        return Response.serverError().entity("id cannot be null").build();
	    }
	    if(!assetRepo.getAssetById(id).isEmpty()) {
	    	assetRepo.deleteAsset(id);   
	    }
	    else{
	    	return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("{\"ID\":\"Invalid\"}").build();
	    }
		try {
			return Response.ok("{\"Asset\":\"Deleted\"}", MediaType.APPLICATION_JSON).build();
		} catch(Exception ex) {
		    return Response.status(500).entity("Deleted the asset "+id).build();
		}
	}


}
