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
	
	

}
