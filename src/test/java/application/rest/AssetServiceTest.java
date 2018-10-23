package application.rest;

import javax.ws.rs.core.Response;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import model.Asset;
import utils.AbstractTest;
import utils.CassandraConnection;


public class AssetServiceTest extends AbstractTest {

	@Before
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
		
        Response create = assetRepo.createAsset(asset);
		
		Assert.assertEquals(create.getStatus(),200);

	}
	
	@Test
	public void updateAssetTest(){

		final CassandraConnection cc = Mockito.spy(CassandraConnection.class);

		Mockito.doNothing().when(cc).getConnection();

		Mockito.when(cc.getSession()).thenReturn(cassandra.session);
		
		Mockito.doNothing().when(cc).close();

		AssetService assetRepo = new AssetService(cc);

		Asset asset = new Asset("3", "UpdWindow", "UpdWindow", "UpdKaspersky", "1.1.1.1", "3.0.1");
		
        Response update = assetRepo.updateAsset(asset, "3");
		
		Assert.assertEquals(update.getStatus(),200);

	}
	
	@Test
	public void deleteAssetTest(){

		final CassandraConnection cc = Mockito.spy(CassandraConnection.class);

		Mockito.doNothing().when(cc).getConnection();

		Mockito.when(cc.getSession()).thenReturn(cassandra.session);
		
		Mockito.doNothing().when(cc).close();

		AssetService assetRepo = new AssetService(cc);
		
        Response delete = assetRepo.deleteAsset("4");
		
        Assert.assertEquals(delete.getStatus(),200);

	}
	
	

}
