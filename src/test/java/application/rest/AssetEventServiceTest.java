package application.rest;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ws.rs.core.Response;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import model.AssetEvent;
import utils.AbstractTest;
import utils.CassandraConnection;

public class AssetEventServiceTest extends AbstractTest {
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void getAssetEventsServiceTest() throws ParseException{
		
		final CassandraConnection cc = Mockito.spy(CassandraConnection.class);

		Mockito.doNothing().when(cc).getConnection();

		Mockito.when(cc.getSession()).thenReturn(cassandra.session);
		
		Mockito.doNothing().when(cc).close();

		AssetEventService assetEventRepo = new AssetEventService(cc);
		
		AssetEvent assetEvent = new AssetEvent("3",3,3,3,3);
		
		List<AssetEvent> list = assetEventRepo.getAssetEvents();

		Assert.assertTrue(list.toString().contains(assetEvent.getId()));
		
	}
	
	@Test
	public void getEventHistoryServiceTest() throws ParseException{
		
		final CassandraConnection cc = Mockito.spy(CassandraConnection.class);

		Mockito.doNothing().when(cc).getConnection();

		Mockito.when(cc.getSession()).thenReturn(cassandra.session);
		
		Mockito.doNothing().when(cc).close();

		AssetEventService assetEventRepo = new AssetEventService(cc);
		
		String fromdate = "2018-09-01 21:01:52";
		SimpleDateFormat datetimeFormatter1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date lFromDate1 = datetimeFormatter1.parse(fromdate);
		Date lFromDate2 = new Date();
		AssetEvent assetEvent = new AssetEvent("1", 1, 1, 1, 1);
		Timestamp fromTS1 = new Timestamp(lFromDate1.getTime());
		Timestamp fromTS2 = new Timestamp(lFromDate2.getTime());
		
		List<AssetEvent> list = assetEventRepo.getEventHistory(fromTS1, fromTS2);

		Assert.assertTrue(list.toString().contains(assetEvent.getId()));
		
	}
	
	@Test
	public void saveEventServiceTest() throws ParseException{
		
		final CassandraConnection cc = Mockito.spy(CassandraConnection.class);

		Mockito.doNothing().when(cc).getConnection();

		Mockito.when(cc.getSession()).thenReturn(cassandra.session);
		
		Mockito.doNothing().when(cc).close();

		AssetEventService assetEventRepo = new AssetEventService(cc);

		AssetEvent assetEvent = new AssetEvent("2", 2, 2, 2, 2);
		
        Response save = assetEventRepo.saveEvent(assetEvent);
		
		Assert.assertEquals(save.getStatus(),200);
		
	}

}
