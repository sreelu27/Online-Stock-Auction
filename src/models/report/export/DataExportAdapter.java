package models.report.export;

import java.util.List;

public class DataExportAdapter extends DataExport{

	@Override
	void emailExport(List<Object> entities) {
		for(Object obj : entities)
		{
			System.out.println(obj.toString());
		}
	}

}
