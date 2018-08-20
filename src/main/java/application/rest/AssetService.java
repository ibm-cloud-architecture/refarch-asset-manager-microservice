package application.rest;

import java.util.ArrayList;
import java.util.List;

import com.datastax.driver.core.Session;

import model.Asset;
import utils.AssetDAOImpl;
import utils.CassandraConnection;

public class AssetService {
	
	CassandraConnection cc  = new CassandraConnection();
	
	public AssetService(CassandraConnection cc){
		this.cc = cc;
	}

	public List<Asset> getAssets(){

		List<Asset> assets = new ArrayList<>();

	    cc.getConnection();
	    Session session = cc.getSession();

	    AssetDAOImpl assetDAO = new AssetDAOImpl(session);

	    assets = assetDAO.getAssets();
	    
	    cc.close();
	    return assets;
	 }

	public List<Asset> getAssetById(String id) {
		
		List<Asset> assets = new ArrayList<>();

	    cc.getConnection();
	    Session session = cc.getSession();

	    AssetDAOImpl assetDAO = new AssetDAOImpl(session);

	    assets = assetDAO.getAssetsById(id);
	    
	    cc.close();
		
		return assets;
	}

	public List<Asset> getAssetsByType(String type) {
		
		List<Asset> assets = new ArrayList<>();

	    cc.getConnection();
	    Session session = cc.getSession();

	    AssetDAOImpl assetDAO = new AssetDAOImpl(session);

	    assets = assetDAO.getAssetsByType(type);
	    
	    cc.close();
		
		return assets;
		
	}

	public String createAsset(Asset asset) {
		
		cc.getConnection();
	    Session session = cc.getSession();

	    AssetDAOImpl assetDAO = new AssetDAOImpl(session);

	    assetDAO.createAsset(asset);
	    
	    cc.close();
	    
	    return "Asset with ID "+asset.getId()+" got created";
		
	}

	public String updateAsset(Asset asset, String id) {
		
		cc.getConnection();
		Session session = cc.getSession();
		
		AssetDAOImpl assetDAO = new AssetDAOImpl(session);
		
		assetDAO.updateAsset(asset, id);
		
		cc.close();
		
		return "Asset with ID "+asset.getId()+" got updated";
		
	}

	public String deleteAsset(String id) {
		
		cc.getConnection();
		Session session = cc.getSession();
		
		AssetDAOImpl assetDAO = new AssetDAOImpl(session);
		
		assetDAO.deleteAsset(id);
		
		cc.close();
		
		return "Asset with ID "+id+" got updated";
		
	}

}
