package application.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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


}
