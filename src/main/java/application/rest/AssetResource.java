package application.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import model.Asset;


@Path("/")
public class AssetResource {
  
  @GET
  @Path("/assets")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getAssets(){
	  AssetService assetRepo = new AssetService();
	  List<Asset> assets = new ArrayList<>();
	  assets = assetRepo.getAssets();
	  return Response.ok(assets).build(); 
  }
  
}
