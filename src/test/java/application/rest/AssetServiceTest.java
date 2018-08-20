package application.rest;

import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import application.rest.AssetService;
import model.Asset;
import utils.AbstractTest;
import utils.CassandraConnection;


public class AssetServiceTest extends AbstractTest {

	@BeforeMethod
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void getAssetsTest(){

		final CassandraConnection cc = Mockito.spy(CassandraConnection.class);

		Mockito.doNothing().when(cc).getConnection();

		Mockito.when(cc.getSession()).thenReturn(cassandra.session);
		
		Mockito.doNothing().when(cc).close();

		AssetService assetRepo = new AssetService(cc);

		Asset asset = new Asset("1", "Window", "Window", "Kaspersky", "0.0.0.0", "1.0.0");

		Assert.assertTrue(assetRepo.getAssets().toString().contains(asset.getType()));

	}
	
	@Test
	public void getAssetById(){

		final CassandraConnection cc = Mockito.spy(CassandraConnection.class);

		Mockito.doNothing().when(cc).getConnection();

		Mockito.when(cc.getSession()).thenReturn(cassandra.session);
		
		Mockito.doNothing().when(cc).close();

		AssetService assetRepo = new AssetService(cc);

		Asset asset = new Asset("1", "Window", "Window", "Kaspersky", "0.0.0.0", "1.0.0");

		Assert.assertTrue(assetRepo.getAssetById("1").toString().contains(asset.getType()));

	}
	
	@Test
	public void getAssetsByTypeTest(){

		final CassandraConnection cc = Mockito.spy(CassandraConnection.class);

		Mockito.doNothing().when(cc).getConnection();

		Mockito.when(cc.getSession()).thenReturn(cassandra.session);
		
		Mockito.doNothing().when(cc).close();

		AssetService assetRepo = new AssetService(cc);

		Asset asset = new Asset("1", "Window", "Window", "Kaspersky", "0.0.0.0", "1.0.0");

		Assert.assertTrue(assetRepo.getAssetsByType("Window").toString().contains(asset.getType()));

	}
	
	@Test
	public void createAssetTest(){

		final CassandraConnection cc = Mockito.spy(CassandraConnection.class);

		Mockito.doNothing().when(cc).getConnection();

		Mockito.when(cc.getSession()).thenReturn(cassandra.session);
		
		Mockito.doNothing().when(cc).close();

		AssetService assetRepo = new AssetService(cc);

		Asset asset = new Asset("0", "test", "test", "test", "0.0.0.0", "1.0.0");
		
        String create = assetRepo.createAsset(asset);
		
		Assert.assertTrue(create.contains(asset.getId()));

	}
	
	@Test
	public void updateAssetTest(){

		final CassandraConnection cc = Mockito.spy(CassandraConnection.class);

		Mockito.doNothing().when(cc).getConnection();

		Mockito.when(cc.getSession()).thenReturn(cassandra.session);
		
		Mockito.doNothing().when(cc).close();

		AssetService assetRepo = new AssetService(cc);

		Asset asset = new Asset("3", "UpdWindow", "UpdWindow", "UpdKaspersky", "1.1.1.1", "3.0.1");
		
        String create = assetRepo.updateAsset(asset, "3");
		
		Assert.assertTrue(create.contains(asset.getId()));

	}
	
	@Test
	public void deleteAssetTest(){

		final CassandraConnection cc = Mockito.spy(CassandraConnection.class);

		Mockito.doNothing().when(cc).getConnection();

		Mockito.when(cc.getSession()).thenReturn(cassandra.session);
		
		Mockito.doNothing().when(cc).close();

		AssetService assetRepo = new AssetService(cc);

		Asset asset = new Asset("4", "Window", "Window", "Kaspersky", "0.0.0.0", "4.0.0");
		
        String delete = assetRepo.deleteAsset("4");
		
		Assert.assertTrue(delete.contains(asset.getId()));

	}
	
	

}
