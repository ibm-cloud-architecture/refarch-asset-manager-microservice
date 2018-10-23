package application.rest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

import model.AssetEvent;
import utils.AbstractTest;

public class AssetEventResourceTest extends AbstractTest {
	
	@Mock
	private AssetEventService assetEventRepo;
	
	@InjectMocks
	AssetEventResource assetEventController = new AssetEventResource();

	@BeforeMethod
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void getAssetEventsResourceTest() throws ParseException{
		
		String fromdate = "2018-09-01 21:01:52";
		SimpleDateFormat datetimeFormatter1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date lFromDate1 = datetimeFormatter1.parse(fromdate);
		AssetEvent assetEvent = new AssetEvent("1",1,1,1,1,lFromDate1);
		List<AssetEvent> asstEvent = new ArrayList<>();
		asstEvent.add(assetEvent);
		Mockito.when(assetEventRepo.getAssetEvents()).thenReturn(asstEvent);
		List<AssetEvent> astEvt = assetEventController.getAssetEvents();
		Assert.assertEquals(astEvt, asstEvent);
	}
	
	@Test
	public void getEventHistoryResourceTest() throws ParseException{
		
		String fromdate = "2018-09-01 21:01:52";
		SimpleDateFormat datetimeFormatter1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date lFromDate1 = datetimeFormatter1.parse(fromdate);
		Date lFromDate2 = new Date();
		String todate = datetimeFormatter1.format(lFromDate2);
		AssetEvent assetEvent = new AssetEvent("1",1,1,1,1,lFromDate1);
		List<AssetEvent> asstEvent = new ArrayList<>();
		asstEvent.add(assetEvent);
		Mockito.when(assetEventRepo.getEventHistory(Mockito.any(),Mockito.any())).thenReturn(asstEvent);
		List<AssetEvent> astEvt = assetEventController.getEventHistory(fromdate, todate);
		Assert.assertEquals(astEvt, asstEvent);
	}
	
	@Test
	public void saveEventResourceTest(){
		
		AssetEvent assetEvent = new AssetEvent("2",2,2,2,2);
		Mockito.when(assetEventRepo.saveEvent(Mockito.any())).thenReturn(Response.ok(assetEvent, MediaType.APPLICATION_JSON).build());
		Response ast = assetEventController.saveEvent(assetEvent);
		Assert.assertEquals(ast.getStatus(),200);
	}
	
	

}
