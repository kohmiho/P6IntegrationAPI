package demo;

import com.primavera.ServerException;
import com.primavera.integration.client.ClientException;
import com.primavera.integration.client.RMIURL;
import com.primavera.integration.client.Session;
import com.primavera.integration.common.DatabaseInstance;
import com.primavera.integration.network.NetworkException;

public class A01_DBInstance {

	/**
	 * Demo how to use LocalMode/RemoteMode to get DB Instances. Review DB
	 * Instance information to make sure you are connecting to the right P6
	 * database
	 * 
	 * @param args
	 * @throws ClientException
	 * @throws NetworkException
	 * @throws ServerException
	 */
	public static void main(String[] args) throws ServerException, NetworkException, ClientException {

		DatabaseInstance[] dbInstances = Session.getDatabaseInstances(A01_DBInstance.getRmiUrl());

		printDBInstance(dbInstances);
	}

	public static String getRmiUrl() {
		if (isRemoteMode)
			return RMIURL.getRmiUrl(RMIURL.STANDARD_RMI_SERVICE, remoteServerHost, remoteServerPort);
		else
			return RMIURL.getRmiUrl(RMIURL.LOCAL_SERVICE);
	}

	private static boolean isRemoteMode = true;
	private static String remoteServerHost = A00_APIConfig.remoteServerHost;
	private static int remoteServerPort = Integer.parseInt(A00_APIConfig.remoteServerPort);

	public static void printDBInstance(DatabaseInstance[] dbInstances) {
		for (DatabaseInstance databaseInstance : dbInstances) {
			System.out.println("DatabaseId=" + databaseInstance.getDatabaseId());
			System.out.println("ActualDatabaseName=" + databaseInstance.getActualDatabaseName());
			System.out.println("DatabaseUrl=" + databaseInstance.getDatabaseUrl());
			System.out.println("DatabaseName=" + databaseInstance.getDatabaseName());
			System.out.println("*******************************************");
		}
	}
}
