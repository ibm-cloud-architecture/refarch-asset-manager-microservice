package utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.ExecutionException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.ResultSetFuture;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.google.common.collect.Lists;
import com.google.common.util.concurrent.ListenableFuture;

import model.Asset;

public class AssetDAOImpl implements AssetDAO {

	private final Session session;

	private final String cassandra_keyspace = "assetmonitoring";
	private final String cassandra_table = "assets";

   public AssetDAOImpl(Session session){
	   this.session=session;
	}

   //Retrieve all Assets
   @Override
   public List<Asset> getAssets(){

       List<Asset> assets = new ArrayList<>();

       ResultSet resultset = null;

       String[] list = {"id"};
       
       List<ResultSetFuture> futures = Lists.newArrayListWithExpectedSize(list.length);
       
       futures.add(session.executeAsync("SELECT * FROM "+cassandra_keyspace+ "."+cassandra_table));
       
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
    	   Asset asset = new Asset();
    	   asset.setId(row.getString("id"));
    	   asset.setAntivirus(row.getString("antivirus"));
    	   asset.setCurrent(row.getLong("current"));
    	   asset.setIpAddress(row.getString("ipaddress"));
    	   asset.setLatitude(row.getString("latitude"));
    	   asset.setLongitude(row.getString("longitude"));
    	   asset.setOs(row.getString("os"));
    	   asset.setPressure(row.getLong("pressure"));
    	   asset.setFlowRate(row.getLong("flowRate"));
    	   asset.setRiskRating(row.getLong("riskRating"));
    	   asset.setRotation(row.getLong("rotation"));
    	   asset.setTemperature(row.getLong("temperature"));
    	   asset.setType(row.getString("type"));
    	   asset.setVersion(row.getString("version"));
    	   asset.setCreationDate(row.getTimestamp("creationDate"));
    	   assets.add(asset);
       }
       
       return assets;
       
   }
   
   //Retrieve Assets by id
   @Override
   public List<Asset> getAssetsById(String id) {
	   
	   List<Asset> assets = new ArrayList<>();
	   
	   ResultSet resultset = null;

       String[] list = {"id"};
       
       List<ResultSetFuture> futures = Lists.newArrayListWithExpectedSize(list.length);
       
       futures.add(session.executeAsync("SELECT * FROM "+cassandra_keyspace+ "."+cassandra_table+" WHERE id = '"+id+"'"));
       
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
    	   Asset asset = new Asset();
    	   asset.setId(row.getString("id"));
    	   asset.setAntivirus(row.getString("antivirus"));
    	   asset.setCurrent(row.getLong("current"));
    	   asset.setIpAddress(row.getString("ipaddress"));
    	   asset.setLatitude(row.getString("latitude"));
    	   asset.setLongitude(row.getString("longitude"));
    	   asset.setOs(row.getString("os"));
    	   asset.setPressure(row.getLong("pressure"));
    	   asset.setFlowRate(row.getLong("flowRate"));
    	   asset.setRiskRating(row.getLong("riskRating"));
    	   asset.setRotation(row.getLong("rotation"));
    	   asset.setTemperature(row.getLong("temperature"));
    	   asset.setType(row.getString("type"));
    	   asset.setVersion(row.getString("version"));
    	   asset.setCreationDate(row.getTimestamp("creationDate"));
    	   assets.add(asset);
    	   
       }
       
       return assets;
       
   }
   
   //Retrieve Assets by type
   @Override
   public List<Asset> getAssetsByType(String type) {
	   
	   List<Asset> assets = new ArrayList<>();
	   
	   ResultSet resultset = null;
	   
	   String[] list = {"id","type"};
	   
	   List<ResultSetFuture> futures = Lists.newArrayListWithExpectedSize(list.length);
	   
	   futures.add(session.executeAsync("SELECT * FROM "+cassandra_keyspace+ "."+cassandra_table+" WHERE type = '"+type+"'"));
	   
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
		   Asset asset = new Asset();
		   asset.setId(row.getString("id"));
		   asset.setAntivirus(row.getString("antivirus"));
		   asset.setCurrent(row.getLong("current"));
		   asset.setIpAddress(row.getString("ipaddress"));
		   asset.setLatitude(row.getString("latitude"));
		   asset.setLongitude(row.getString("longitude"));
		   asset.setOs(row.getString("os"));
		   asset.setPressure(row.getLong("pressure"));
    	   asset.setFlowRate(row.getLong("flowRate"));
		   asset.setPressure(row.getLong("pressure"));
		   asset.setRotation(row.getLong("rotation"));
		   asset.setTemperature(row.getInt("temperature"));
		   asset.setType(row.getString("type"));
		   asset.setVersion(row.getString("version"));
		   asset.setCreationDate(row.getTimestamp("creationDate"));
		   assets.add(asset);
		   
	   }
	   
	   return assets;
	   
   }
   
   //Create an Asset
   @Override
   public Response createAsset(Asset asset) {
	   
	   Date astCreationDate = asset.getCreationDate();

	   SimpleDateFormat cassandrarDtFormat;
	   cassandrarDtFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
	   cassandrarDtFormat.setTimeZone(TimeZone.getTimeZone("CET"));
	   
	   String createDate = cassandrarDtFormat.format(astCreationDate);
	   
	   session.executeAsync("insert into "+cassandra_keyspace+ "."+cassandra_table
				+ "(id, os, type, ipaddress, version, antivirus, current, rotation, pressure, flowRate, temperature, riskRating, latitude, longitude, creationDate) "
				+ "values ('"+asset.getId()+"', '"+asset.getOs()+"', '"+asset.getType()+"', '"+asset.getIpAddress()+"', '"+asset.getVersion()+"',"
				+ " '"+asset.getAntivirus()+"', "+asset.getCurrent()+", "+asset.getRotation()+", "+asset.getPressure()+","+asset.getFlowRate()+", "+asset.getTemperature()+""
				+ ", "+asset.getRiskRating()+", '"+asset.getLatitude()+"', '"+asset.getLongitude()+"','"+createDate+"');");
	   
	   return Response.ok(asset, MediaType.APPLICATION_JSON).build(); 
	      
   }
   
   //Update an Asset
   @Override
   public Response updateAsset(Asset asset, String id) {
	   
	   session.executeAsync("update assetmonitoring.assets set os='"+asset.getOs()+"', type='"+asset.getType()+"',"
				+ " ipaddress='"+asset.getIpAddress()+"', version='"+asset.getVersion()+"', antivirus='"+asset.getAntivirus()+"',"
				+ " current="+asset.getCurrent()+", rotation="+asset.getRotation()+", pressure="+asset.getPressure()+", flowRate="+asset.getFlowRate()+", temperature="+asset.getTemperature()+""
				+ ", riskRating="+asset.getRiskRating()+", latitude="+asset.getLatitude()+", longitude="+asset.getLongitude()+" where id='"+id+"'");
	   
	   return Response.ok(asset, MediaType.APPLICATION_JSON).build(); 
	   
   }
   
   //Delete an asset
   @Override
   public Response deleteAsset(String id) {
	   
	   session.executeAsync("delete from assetmonitoring.assets WHERE id='"+id+"'");
	   
	   return Response.ok("{\"Asset\":\"Deleted\"}", MediaType.APPLICATION_JSON).build();

	}

}
