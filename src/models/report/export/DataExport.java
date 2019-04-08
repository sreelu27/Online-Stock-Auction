package models.report.export;

import java.util.List;

public abstract class DataExport {
	// FrameworkClass <<Target>>
	
	public void pdfExport(List<Object> entities)
	{
		for(Object obj : entities)
		{
			System.out.println(obj.toString());
		}
	}
	
	public void excelExport(List<Object> entities)
	{
		// implement excel export
	}
	
	// not implemented method
	public abstract void emailExport(List<Object> entities,List<String> emailList);
	

}
