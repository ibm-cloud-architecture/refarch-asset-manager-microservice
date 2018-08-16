package application.rest;

import java.util.ArrayList;
import java.util.List;

import com.datastax.driver.core.Session;

import model.Asset;
import utils.AssetDAOImpl;
import utils.CassandraConnection;

public class AssetService {
	
	CassandraConnection cc;
	
	public AssetService(CassandraConnection cc) {
	    this.cc = cc;
	  }
	
	public List<Asset> getAssets(){
	
		List<Asset> assets = new ArrayList<>();
		
	    cc.getConnection();
	    Session session = cc.getSession();
		
	    AssetDAOImpl assetDAO = new AssetDAOImpl(session);
	    
	    assets = assetDAO.getAssets();
	    return assets;
	 }

}
