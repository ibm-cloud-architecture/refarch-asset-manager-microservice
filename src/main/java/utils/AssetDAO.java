package utils;

import java.util.List;

import javax.enterprise.context.RequestScoped;

import model.Asset;

public interface AssetDAO {
	List<Asset> getAssets();
	List<Asset> getAssetsById(String id);
	List<Asset> getAssetsByType(String type);
	void createAsset(Asset asset);
	void updateAsset(Asset asset, String id);
	void deleteAsset(String id);
	
}
