package utils;

import java.util.List;

import model.Asset;

public interface AssetDAO {
	
	List<Asset> getAssets();
	List<Asset> getAssetsById(String id);
	List<Asset> getAssetsByType(String type);
	String createAsset(Asset asset);
	String updateAsset(Asset asset, String id);
	String deleteAsset(String id);

}
