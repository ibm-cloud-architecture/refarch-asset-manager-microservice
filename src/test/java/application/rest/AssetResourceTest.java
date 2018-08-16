package application.rest;

import java.util.ArrayList;
import java.util.List;

import org.mockito.InjectMocks;
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

public class AssetResourceTest extends AbstractTest  {

	@Mock
	private AssetService assetRepo;
	
	@InjectMocks
	AssetResource assetController = new AssetResource();

	@BeforeMethod
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void getAssetsTest(){
		
		Asset asset = new Asset("1", "Window", "Window", "Kaspersky", "0.0.0.0", "1.0.0");
		List<Asset> asst = new ArrayList<>();
        asst.add(asset);
		Mockito.when(assetRepo.getAssets()).thenReturn(asst);
		List<Asset> ast = assetController.getAssets();
		Assert.assertEquals(ast, asst);
	}

}
