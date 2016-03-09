package demo;

import java.util.ResourceBundle;

public class A00_APIConfig {

	private static final ResourceBundle bundle = ResourceBundle.getBundle(A00_APIConfig.class.getName());

	public static String remoteServerHost = bundle.getString("REMOTE_SERVER_HOST");
	public static String remoteServerPort = bundle.getString("REMOTE_SERVER_PORT");
	public static String p6UserName = bundle.getString("P6_API_USER");;
	public static String p6Password = bundle.getString("P6_API_PASSWORD");;

}
