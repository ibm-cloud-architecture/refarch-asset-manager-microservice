package utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ws.rs.core.Response;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import model.AssetEvent;

public class AssetEventDAOImplTest extends AbstractTest {
	
	private AssetEventDAOImpl assetEventDAOImpl;
	
	@Before
	public  void setUp() throws Exception {
		assetEventDAOImpl = new AssetEventDAOImpl(cassandra.session);
	}
	
	@Test
	public void getAssetEventsTest(){
		
		AssetEvent assetEvent = new AssetEvent("1", 1, 1, 1, 1);
		List<AssetEvent> assts = assetEventDAOImpl.getAssetEvents();
		
		Assert.assertTrue(assts.toString().contains(assetEvent.getId()));
	
	}
	
	@Test
	public void getEventHistoryTest() throws Exception {
		
		String fromdate = "2018-09-01 21:01:52";
		SimpleDateFormat datetimeFormatter1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date lFromDate1 = datetimeFormatter1.parse(fromdate);
		Date lFromDate2 = new Date();
		AssetEvent assetEvent = new AssetEvent("1", 1, 1, 1, 1);
		Timestamp fromTS1 = new Timestamp(lFromDate1.getTime());
		Timestamp fromTS2 = new Timestamp(lFromDate2.getTime());
		
		List<AssetEvent> assts1 = assetEventDAOImpl.getEventHistory(fromTS1, fromTS2);
		
		Assert.assertTrue(assts1.toString().contains(assetEvent.getId()));
	
	}
	
	@Test
	public void saveEventDAOTest(){
		
		AssetEvent asstEvent = new AssetEvent("2",2,2,2,2);
		Response save = assetEventDAOImpl.saveEvent(asstEvent);
		Assert.assertEquals(save.getStatus(),200);
		
	}

}
