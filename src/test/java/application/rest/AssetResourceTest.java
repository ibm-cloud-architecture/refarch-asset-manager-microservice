package application.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
	
	@Test
	public void getAssetByIdTest(){
		
		Asset asset = new Asset("1", "Window", "Window", "Kaspersky", "0.0.0.0", "1.0.0");
		List<Asset> asst = new ArrayList<>();
        asst.add(asset);
		Mockito.when(assetRepo.getAssetById(Mockito.anyString())).thenReturn(asst);
		List<Asset> ast = assetController.getAssetById("1");
		Assert.assertEquals(ast, asst);
	}
	
	@Test
	public void getAssetsByTypeTest(){
		
		Asset asset = new Asset("1", "Window", "Window", "Kaspersky", "0.0.0.0", "1.0.0");
		List<Asset> asst = new ArrayList<>();
        asst.add(asset);
		Mockito.when(assetRepo.getAssetsByType(Mockito.anyString())).thenReturn(asst);
		List<Asset> ast = assetController.getAssetsByType("Window");
		Assert.assertEquals(ast, asst);
	}
	
	@Test
	public void createAssetTest(){
		
		Asset asset = new Asset("0", "test", "test", "test", "0.0.0.0", "1.0.0");
		Mockito.when(assetRepo.createAsset(Mockito.anyObject())).thenReturn(Response.ok(asset, MediaType.APPLICATION_JSON).build());
		Response ast = assetController.createAsset(asset);
		Assert.assertEquals(ast.getStatus(),200);
	}
	
	@Test
	public void updateAssetTest(){
		
		Asset asset = new Asset("3", "UpdWindow", "UpdWindow", "UpdKaspersky", "1.1.1.1", "3.0.1");
		List<Asset> asst = new ArrayList<>();
        asst.add(asset);
		Mockito.when(assetRepo.getAssetById(Mockito.anyString())).thenReturn(asst);
		Mockito.when(assetRepo.updateAsset(Mockito.anyObject(), Mockito.anyString())).thenReturn(Response.ok(asset, MediaType.APPLICATION_JSON).build());
		Response ast = assetController.updateAsset(asset, "3");
		Assert.assertEquals(ast.getStatus(),200);
	}
	
	@Test
	public void deleteAssetTest(){
		Asset asset = new Asset("4", "Window", "Window", "Kaspersky", "0.0.0.0", "4.0.0");
		List<Asset> asst = new ArrayList<>();
        asst.add(asset);
		Mockito.when(assetRepo.getAssetById(Mockito.anyString())).thenReturn(asst);
		Mockito.when(assetRepo.deleteAsset(Mockito.anyString())).thenReturn(Response.ok("{\"Asset\":\"Deleted\"}", MediaType.APPLICATION_JSON).build());
		Response ast = assetController.deleteAsset("4");
		Assert.assertEquals(ast.getStatus(),200);
	}

}
