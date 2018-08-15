package rest;


import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import application.rest.AssetService;
import model.Asset;


public class AssetServiceTest {
	
	private AssetService assetRepo;
	
	@BeforeClass
    public void setUp() throws Exception {
		assetRepo = new AssetService();
    }
	
	@Test
	public void getAssetsTest(){
		
		//Existing assets in testdb
		Asset asset = new Asset("1", "Window", "Window", "Kaspersky", "0.0.0.0", "1.0.0");
				 
		//List<Asset> list = new ArrayList<>();
				        
		//list.add(asset);
				
				
		//Mockito.when(assetRepo.getAssets()).thenReturn(list);
				
		assertEquals(assetRepo.getAssets().toString().contains(asset.getType()), true);
		
	}

}
