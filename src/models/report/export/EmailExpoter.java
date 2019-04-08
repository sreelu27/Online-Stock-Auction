package models.report.export;

import java.util.List;

public class EmailExpoter {
	//<<Adaptee>> this class having the functionality that is going to be used by <<Adapter>>
	public void exportToEmail(List<String> emailList,EmailConnector connector,List<Object> entities)
	{
		for(String email : emailList)
		{
			System.out.println("Email sending to "+email);
			for(Object obj : entities)
			{
				System.out.println(obj.toString());
			}
		}
	}
	
	public EmailConnector connectToEmailServer()
	{
		System.out.println("Connecting to email server..!!");
		return new EmailConnector();
	}

}
