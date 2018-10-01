package utils;

import java.util.List;

import javax.ws.rs.core.Response;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import model.Asset;

public class AssetDAOImplTest extends AbstractTest {
	
	private AssetDAOImpl assetDAOImpl;
	
	@BeforeClass
	public void setUp() throws Exception {
		assetDAOImpl = new AssetDAOImpl(cassandra.session);
	}

	@Test
	public void getAssetsTest() throws Exception {
		
		//Existing assets in testdb
		Asset asset = new Asset("1", "Window", "Window", "Kaspersky", "0.0.0.0", "1.0.0");
		
		List<Asset> list = assetDAOImpl.getAssets();
		
		Assert.assertTrue(list.toString().contains(asset.getType()));
	
	}

	@Test
	public void getAssetsByIdTest() throws Exception {
		
		//Existing assets in testdb
		Asset asset = new Asset("1", "Window", "Window", "Kaspersky", "0.0.0.0", "1.0.0");
		
		List<Asset> list = assetDAOImpl.getAssetsById("1");
		
		Assert.assertTrue(list.toString().contains(asset.getId()));
	
	}
	
	@Test
	public void getAssetsByTypeTest() throws Exception {
		
		//Existing assets in testdb
		Asset asset = new Asset("1", "Window", "Window", "Kaspersky", "0.0.0.0", "1.0.0");
		
		List<Asset> list = assetDAOImpl.getAssetsByType("Window");
		
		Assert.assertTrue(list.toString().contains(asset.getType()));
		
	}

	@Test
	public void createAssetTest() throws Exception {
		
		Asset asset = new Asset("0", "test", "test", "test", "0.0.0.0", "1.0.0");
		
		Response create = assetDAOImpl.createAsset(asset);
	
		Assert.assertTrue(create.getStatus()==200);
	}

	@Test
	public void updateAssetTest() throws Exception {
		
		Asset asset = new Asset("3", "UpdWindow", "UpdWindow", "UpdKaspersky", "1.1.1.1", "3.0.1");
		
		Response update = assetDAOImpl.updateAsset(asset, "3");
	
		Assert.assertTrue(update.getStatus()==200);
		
	}

	@Test
	public void deleteAssetTest() throws Exception {
		
		Response delete = assetDAOImpl.deleteAsset("4");
	
		Assert.assertTrue(delete.getStatus()==200);

	}

}
