package models.report.export;

import java.util.List;

public abstract class DataExport {
	// FrameworkClass <target>
	
	void pdfExport(List<Object> entities)
	{
		for(Object obj : entities)
		{
			System.out.println(obj.toString());
		}
	}
	
	void excelExport(List<Object> entities)
	{
		// implement excel export
	}
	
	abstract void emailExport(List<Object> entities);
	

}
