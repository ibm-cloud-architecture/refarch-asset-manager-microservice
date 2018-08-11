package utils;

import java.util.ArrayList;
import java.util.List;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.Statement;
import com.datastax.driver.core.querybuilder.QueryBuilder;

import model.Asset;

public class AssetDAOImpl implements AssetDAO {
	
	private final Session session;
	
	private final String cassandra_keyspace = "assetmonitoring";
	private final String cassandra_table = "assets";
	
    public AssetDAOImpl(Session session){
		this.session=session;
	}
	
    @Override
	public List<Asset> getAssets(){
		
		List<Asset> assets = new ArrayList<>();
		
		ResultSet resultset = session.execute("SELECT * FROM "+cassandra_keyspace+ "."+cassandra_table);
		for (Row row : resultset) {
			Asset asset = new Asset();
			asset.setId(row.getString("id"));
			asset.setAntivirus(row.getString("antivirus"));
			asset.setCurrent(row.getDouble("current"));
			asset.setIpAddress(row.getString("ipaddress"));
			asset.setLatitude(row.getDouble("latitude"));
			asset.setLongitude(row.getDouble("longitude"));
			asset.setOs(row.getString("os"));
			asset.setPressure(row.getInt("pressure"));
			asset.setRotation(row.getInt("rotation"));
			asset.setTemperature(row.getInt("temperature"));
			asset.setType(row.getString("type"));
			asset.setVersion(row.getString("version"));
			assets.add(asset);
	    }
		
		System.out.println(""+assets);
		
		return assets;
		
	}

	@Override
	public List<Asset> getAssetsById(String id) {
		
		List<Asset> assets = new ArrayList<>();
		
		//Retrieve Assets by id
		ResultSet resultset = session.execute("SELECT * FROM "+cassandra_keyspace+ "."+cassandra_table+" WHERE id = '"+id+"'");
		for (Row row : resultset) {
			Asset asset = new Asset();
			asset.setId(row.getString("id"));
			asset.setAntivirus(row.getString("antivirus"));
			asset.setCurrent(row.getDouble("current"));
			asset.setIpAddress(row.getString("ipaddress"));
			asset.setLatitude(row.getDouble("latitude"));
			asset.setLongitude(row.getDouble("longitude"));
			asset.setOs(row.getString("os"));
			asset.setPressure(row.getInt("pressure"));
			asset.setRotation(row.getInt("rotation"));
			asset.setTemperature(row.getInt("temperature"));
			asset.setType(row.getString("type"));
			asset.setVersion(row.getString("version"));
			assets.add(asset);
	    }
		
		System.out.println(""+assets);
		
		return assets;
	}

	@Override
	public List<Asset> getAssetsByType(String type) {
		
		List<Asset> assets = new ArrayList<>();
		
		//Retrieve Assets
		ResultSet resultset = session.execute("SELECT * FROM "+cassandra_keyspace+ "."+cassandra_table+" WHERE type = '"+type+"'");
		for (Row row : resultset) {
			Asset asset = new Asset();
			asset.setId(row.getString("id"));
			asset.setAntivirus(row.getString("antivirus"));
			asset.setCurrent(row.getDouble("current"));
			asset.setIpAddress(row.getString("ipaddress"));
			asset.setLatitude(row.getDouble("latitude"));
			asset.setLongitude(row.getDouble("longitude"));
			asset.setOs(row.getString("os"));
			asset.setPressure(row.getInt("pressure"));
			asset.setRotation(row.getInt("rotation"));
			asset.setTemperature(row.getInt("temperature"));
			asset.setType(row.getString("type"));
			asset.setVersion(row.getString("version"));
			assets.add(asset);
	    }
		
		System.out.println(""+assets);
		
		return assets;
	}

	@Override
	public void createAsset(Asset asset) {
		
		//Create an Asset
		session.execute("insert into assetmonitoring.assets "
				+ "(id, os, type, ipaddress, version, antivirus, current, rotation, pressure, temperature, latitude, longitude) "
				+ "values ('"+asset.getId()+"', '"+asset.getOs()+"', '"+asset.getType()+"', '"+asset.getIpAddress()+"', '"+asset.getVersion()+"',"
				+ " '"+asset.getAntivirus()+"', "+asset.getCurrent()+", "+asset.getRotation()+", "+asset.getPressure()+", "+asset.getTemperature()+""
				+ ", "+asset.getLatitude()+", "+asset.getLongitude()+");");
		
	}

	@Override
	public void updateAsset(Asset asset, String id) {
		
		//Update an Asset
		session.execute("update assetmonitoring.assets set os='"+asset.getOs()+"', type='"+asset.getType()+"',"
				+ " ipaddress='"+asset.getIpAddress()+"', version='"+asset.getVersion()+"', antivirus='"+asset.getAntivirus()+"',"
				+ " current="+asset.getCurrent()+", rotation="+asset.getRotation()+", pressure="+asset.getPressure()+", temperature="+asset.getTemperature()+""
				+ ", latitude="+asset.getLatitude()+", longitude="+asset.getLongitude()+" where id='"+id+"'");
		
	}

	@Override
	public void deleteAsset(String id) {
		
		//Delete an asset
		session.execute("delete from assetmonitoring.assets WHERE id='"+id+"'");
		
	}

}
