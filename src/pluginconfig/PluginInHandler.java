package pluginconfig;

import java.util.ArrayList;

import org.plugface.core.PluginManager;
import org.plugface.core.factory.PluginManagers;
import org.plugface.core.factory.PluginSources;

import ie.ul.interfaces.Chart;


public class PluginInHandler
{
	private static PluginInHandler initializer;
	private static PluginManager manager;
	
	private PluginInHandler()
	{
		
	}
	
	public static PluginInHandler getInstance()
	{
		if(initializer == null)
		{
			initializer = new PluginInHandler();
			loadPluginLibrary();
		}
		return initializer;
	}
	
	private static void loadPluginLibrary()
	{
		manager = PluginManagers.defaultPluginManager();
		try
		{
			manager.loadPlugins(PluginSources.jarSource("file:///E:/EE/UL/OnlineStockAuction/libs/"));
		}
		catch ( Exception e )
		{
			e.printStackTrace();
		}
		
	}
	
	public PluginManager getPluginManager()
	{
		return manager;
	}
	
}
