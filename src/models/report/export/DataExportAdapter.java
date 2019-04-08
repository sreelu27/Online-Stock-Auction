package models.report.export;

import java.util.List;

public class DataExportAdapter extends DataExport{

	//<<Adapter>>
	private EmailExpoter emailExpoter;
	
	public DataExportAdapter(EmailExpoter emailExpoter)
	{
		this.emailExpoter = emailExpoter;
	}
	
	// This method uses the functionality coming from Adaptee which is EmailExporter Class
	@Override
	public void emailExport(List<Object> entities,List<String> emailList) {
		emailExpoter.exportToEmail( emailList, emailExpoter.connectToEmailServer(), entities );
	}

}
