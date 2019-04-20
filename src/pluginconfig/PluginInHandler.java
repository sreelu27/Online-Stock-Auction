package pluginconfig;

import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;

import org.apache.commons.io.FilenameUtils;
import org.plugface.core.PluginManager;
import org.plugface.core.factory.PluginManagers;
import org.plugface.core.factory.PluginSources;

import controllers.plugininit.CustomPluginManager;
import controllers.plugininit.CustomPluginSource;
import ie.ul.interfaces.Chart;


public class PluginInHandler
{
	private static PluginInHandler initializer;
	private static PluginManager manager;
	private static ServletContext context;
	
	private PluginInHandler()
	{
		
	}
	
	public static PluginInHandler getInstance(ServletContext context, HttpServlet servlet)
	{
		if(initializer == null)
		{
			initializer = new PluginInHandler();
			PluginInHandler.context = context;
			loadPluginLibrary(servlet);
			
		}
		return initializer;
	}
	
	private static void loadPluginLibrary(HttpServlet servlet)
	{
		manager = CustomPluginManager.defaultPluginManager();
		try
		{
			String fullPath = context.getRealPath("/WEB-INF/lib/");
			System.out.println( fullPath );
			//manager.loadPlugins(PluginSources.jarSource("file:///"+FilenameUtils.separatorsToUnix(fullPath)));
			//manager.loadPlugins(CustomPluginSource.jarSource("file:///"+FilenameUtils.separatorsToUnix(fullPath),servlet));
			manager.loadPlugins(CustomPluginSource.jarSource("file:///C:/PluginArchi/",servlet));
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
	
	public static void main( String[] args )
	{
		manager = PluginManagers.defaultPluginManager();
		try
		{
//			String fullPath = context.getRealPath("/WEB-INF/lib/");
//			System.out.println( fullPath );
			//manager.loadPlugins(PluginSources.jarSource("file:///"+FilenameUtils.separatorsToUnix(fullPath)));
			//manager.loadPlugins(PluginSources.jarSource("file:///"+FilenameUtils.separatorsToUnix(fullPath)));
			manager.loadPlugins(PluginSources.jarSource("file:///E:/EE/UL/PluginJars/"));
			///OnlineStockAuction/WebContent/WEB-INF/lib
			Chart chart = manager.getPlugin(Chart.class); 
			chart.draw( new ArrayList<>());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
