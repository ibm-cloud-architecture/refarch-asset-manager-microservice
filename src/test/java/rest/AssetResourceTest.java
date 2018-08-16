package rest;

import java.util.ArrayList;
import java.util.List;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import application.rest.AssetResource;
import application.rest.AssetService;
import model.Asset;
import utils.AbstractTest;
import utils.CassandraConnection;

public class AssetResourceTest extends AbstractTest  {
	
	@Mock
	AssetService a;
	
	@BeforeMethod
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void getAssetsTest() throws Exception{
		
		final CassandraConnection cc = Mockito.spy(CassandraConnection.class);
		
        Mockito.doNothing().when(cc).getConnection();
		
		Mockito.when(cc.getSession()).thenReturn(cassandra.session);
		
		AssetService object = new AssetService(cc);
		final AssetService spy = Mockito.spy(object);
		
		Asset asset = new Asset("1", "Window", "Window", "Kaspersky", "0.0.0.0", "1.0.0");
		
		List<Asset> asst = new ArrayList<>();
		
		asst.add(asset);
		
		Mockito.doReturn(asst).when(spy).getAssets();
		
		AssetResource asstres = new AssetResource(spy);
		
		List<Asset> assets = asstres.getAssets();
		
		Assert.assertTrue(assets.toString().contains(asset.getType()));
		
	}


}
