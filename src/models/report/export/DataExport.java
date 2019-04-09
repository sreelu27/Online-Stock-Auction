package models.report.export;

import java.util.List;

public abstract class DataExport {
	// FrameworkClass <<Target>>
	
	protected String lastTimeStamp="NO PREVIOUS REPORTS EXPORTED";
	
	public String getLastReportTimestamp()
	{
		return lastTimeStamp;
	}
	
	// not implemented method
	public abstract void exportData(List<Object> entities,List<String> emailList);
	

}
