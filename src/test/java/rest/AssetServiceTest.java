package rest;

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
	    
		AssetService assetRepo = new AssetService(cc);
		
		Asset asset = new Asset("1", "Window", "Window", "Kaspersky", "0.0.0.0", "1.0.0");
		
		System.out.println("assets"+assetRepo.getAssets().toString());
		
		Assert.assertTrue(assetRepo.getAssets().toString().contains(asset.getType()));
		
	}

}
