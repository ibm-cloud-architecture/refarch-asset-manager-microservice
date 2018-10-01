package utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import model.AssetEvent;

public class AssetEventDAOImplTest extends AbstractTest {
	
    private AssetEventDAOImpl assetEventDAOImpl;
	
	private volatile boolean value = false;
	
	@BeforeClass
	public void setUp() throws Exception {
		assetEventDAOImpl = new AssetEventDAOImpl(cassandra.session);
	}
	
//	@Test
//	public void getAssetEventsTest() throws Exception {
//		
//		//Existing assetEvents in testdb
//		String fromdate = "2018-09-19 11:20:59";
//		SimpleDateFormat datetimeFormatter1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//		Date lFromDate1 = datetimeFormatter1.parse(fromdate);
//		AssetEvent assetEvent = new AssetEvent("1",1,1,1,1,lFromDate1);
//		List<AssetEvent> list = assetEventDAOImpl.getAssetEvents();
//		
//		Assert.assertTrue(list.toString().contains(assetEvent.getId()));
//	
//	}
	
//	@Test
//	public void getAssetsTest() throws Exception {
//		
//		String fromdate = "2018-09-19 11:23:20";
//		SimpleDateFormat datetimeFormatter1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//		Date lFromDate1 = datetimeFormatter1.parse(fromdate);
//		Date lFromDate2 = new Date();
//		AssetEvent assetEvent = new AssetEvent("1", 1, 1, 1, 1, lFromDate1);
//		Timestamp fromTS1 = new Timestamp(lFromDate1.getTime());
//		Timestamp fromTS2 = new Timestamp(lFromDate2.getTime());
//		
//		List<AssetEvent> list = assetEventDAOImpl.getEventHistory(fromTS1, fromTS2);
//		
//		while(value == false)
//		{
//			if(!list.isEmpty()){
//				value = true;
//			}
//		  Thread.sleep(1000);
//		}
//		
//		Assert.assertTrue(list.toString().contains(assetEvent.getId()));
//	
//	}
	
//	@Test
//	public void saveEventTest(){
//		
//		AssetEvent assetEvent = new AssetEvent("2",2,2,2,2);
//		Response save = assetEventDAOImpl.saveEvent(assetEvent);
//		
//		while(value == false){
//			if(save.getStatus() == 200){
//				value = true;
//			}
//		}
//		
//		Assert.assertTrue(assetEventDAOImpl.getAssets().contains(assetEvent));
//		
//	}

}
