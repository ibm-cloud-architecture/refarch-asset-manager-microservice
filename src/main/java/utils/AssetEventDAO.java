package utils;

import java.sql.Timestamp;
import java.util.List;

import javax.ws.rs.core.Response;

import model.AssetEvent;

public interface AssetEventDAO {
	
	List<AssetEvent> getAssetEvents();
	List<AssetEvent> getEventHistory(Timestamp fromDate, Timestamp toDate);
	Response saveEvent(AssetEvent assetEvent);

}
