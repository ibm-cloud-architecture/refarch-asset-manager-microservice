package utils;

import java.util.List;

import javax.ws.rs.core.Response;

import model.Asset;

public interface AssetDAO {
	
	List<Asset> getAssets();
	List<Asset> getAssetsById(String id);
	List<Asset> getAssetsByType(String type);
	Response createAsset(Asset asset);
	Response updateAsset(Asset asset, String id);
	Response deleteAsset(String id);

}
