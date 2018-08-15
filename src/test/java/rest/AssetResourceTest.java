package rest;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import application.rest.AssetResource;
import application.rest.AssetService;
import model.Asset;

public class AssetResourceTest {
	
private AssetResource assetResource;

private AssetService assetRepo;
	
	@BeforeClass
    public void setUp() throws Exception {
		assetResource = new AssetResource();
	    assetRepo = Mockito.mock(AssetService.class);
    }
	
	@Test
	public void getAssetsTest(){
		
		//Existing assets in testdb
		Asset asset = new Asset("1", "Window", "Window", "Kaspersky", "0.0.0.0", "1.0.0");
		 
		List<Asset> list = new ArrayList<>();
		        
		list.add(asset);
		
		
		Mockito.when(assetRepo.getAssets()).thenReturn(list);
		
		assertEquals(assetResource.getAssets().toString().contains(asset.getType()), true);
		
		
	}

}
