package pluginconfig;

import java.net.URI;

import javax.servlet.ServletContext;

import org.apache.commons.io.FilenameUtils;
import org.plugface.core.PluginManager;
import org.plugface.core.factory.PluginManagers;
import org.plugface.core.factory.PluginSources;


public class PluginInHandler
{
	private static PluginInHandler initializer;
	private static PluginManager manager;
	private static ServletContext context;
	
	private PluginInHandler()
	{
		
	}
	
	public static PluginInHandler getInstance(ServletContext context)
	{
		if(initializer == null)
		{
			initializer = new PluginInHandler();
			PluginInHandler.context = context;
			loadPluginLibrary();
			
		}
		return initializer;
	}
	
	private static void loadPluginLibrary()
	{
		manager = PluginManagers.defaultPluginManager();
		try
		{
			String fullPath = context.getRealPath("/WEB-INF/lib/");
			//manager.loadPlugins(PluginSources.jarSource("file:///"+FilenameUtils.separatorsToUnix(fullPath)));
			manager.loadPlugins(PluginSources.jarSource("file:///"+FilenameUtils.separatorsToUnix(fullPath)));
			///OnlineStockAuction/WebContent/WEB-INF/lib
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
