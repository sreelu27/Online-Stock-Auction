package models.report.export;

import java.util.Date;
import java.util.List;

public class EmailDataExportAdapter extends DataExport{

	//<<Adapter>>
	private EmailExpoter emailExpoter;
	
	public EmailDataExportAdapter(EmailExpoter emailExpoter)
	{
		this.emailExpoter = emailExpoter;
	}
	
	// This method uses the functionality coming from Adaptee which is EmailExporter Class
	@Override
	public void exportData(List<Object> entities,List<String> emailList) {
		lastTimeStamp = new Date().toString();
		emailExpoter.exportToEmail( emailList, emailExpoter.connectToEmailServer(), entities );
	}

}
