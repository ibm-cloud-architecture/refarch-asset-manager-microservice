package it;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.junit.Test;

public class AssetResourceTest {
	
	@Test
	public void getAssetsTest() throws IOException{
		URL url = new URL("http://localhost:9080/assetmanager/assets");
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET"); 
		int responseCode = con.getResponseCode();
		assertEquals(responseCode,200);
	}

}
