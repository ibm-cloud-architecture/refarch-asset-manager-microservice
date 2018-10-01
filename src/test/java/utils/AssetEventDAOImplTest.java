package utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ws.rs.core.Response;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import model.Asset;
import model.AssetEvent;

public class AssetEventDAOImplTest extends AbstractTest {
	
    private AssetEventDAOImpl assetEventDAOImpl;
	
	@BeforeClass
	public void setUp() throws Exception {
		assetEventDAOImpl = new AssetEventDAOImpl(cassandra.session);
	}
	
	@Test
	public void getAssetEventsTest() throws Exception {
		
		AssetEvent assetEvent = new AssetEvent("3",3,3,3,3);
		List<AssetEvent> list = assetEventDAOImpl.getAssetEvents();
		
		Assert.assertTrue(list.toString().contains(assetEvent.getId()));
	
	}
	
	@Test
	public void getEventHistoryTest() throws Exception {
		
		String fromdate = "2018-09-01 21:01:52";
		SimpleDateFormat datetimeFormatter1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date lFromDate1 = datetimeFormatter1.parse(fromdate);
		Date lFromDate2 = new Date();
		AssetEvent assetEvent = new AssetEvent("1", 1, 1, 1, 1, lFromDate1);
		Timestamp fromTS1 = new Timestamp(lFromDate1.getTime());
		Timestamp fromTS2 = new Timestamp(lFromDate2.getTime());
		
		List<AssetEvent> list = assetEventDAOImpl.getEventHistory(fromTS1, fromTS2);
		
		Assert.assertTrue(list.toString().contains(assetEvent.getId()));
	
	}
	
	@Test
	public void saveEventTest() throws InterruptedException, ParseException{
		
		AssetEvent assetEvent = new AssetEvent("2",2,2,2,2);
		Response save = assetEventDAOImpl.saveEvent(assetEvent);
		
		Assert.assertTrue(save.getStatus()==200);
		
	}

}
