package pluginconfig;

import java.util.ArrayList;

import org.plugface.core.PluginManager;
import org.plugface.core.factory.PluginManagers;
import org.plugface.core.factory.PluginSources;

import pluginconfig.plugininterfaces.Chart;

public class PluginInitializer
{
	private static PluginInitializer initializer;
	
	private PluginInitializer()
	{
		
	}
	
	public static PluginInitializer getInstance()
	{
		if(initializer == null)
		{
			initializer = new PluginInitializer();
		}
		return initializer;
	}
	
	private static void loadPluginLibrary()
	{
		
	}
	
	public static void main( String[] args )
	{
		PluginManager manager = PluginManagers.defaultPluginManager();
		try
		{
			manager.loadPlugins(PluginSources.jarSource("path/to/plugin/folder"));
		}
		catch ( Exception e )
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Chart chart = manager.getPlugin(Chart.class); 
		chart.draw( new ArrayList<>());
	}
}
