package orb;

import org.omg.CORBA.LocalObject;
import org.omg.PortableInterceptor.ORBInitInfo;
import org.omg.PortableInterceptor.ORBInitializer;

public class InterceptorORBInitializer extends LocalObject implements ORBInitializer
{
	public static CustomInterceptor interceptor;

	public String name()
	{
		return "";
	}

	public void pre_init( ORBInitInfo info )
	{
		try
		{
			interceptor = new CustomInterceptor();
			info.add_client_request_interceptor( interceptor );
			//info.add_server_request_interceptor( interceptor );
		}
		catch ( Exception ex )
		{
		}
	}

	public void post_init( ORBInitInfo info )
	{
	}

}
