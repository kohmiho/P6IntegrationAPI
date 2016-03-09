package demo;

import com.primavera.ServerException;
import com.primavera.integration.client.ClientException;
import com.primavera.integration.client.Session;
import com.primavera.integration.common.DatabaseInstance;
import com.primavera.integration.network.NetworkException;

public class A02_Session {

	/**
	 * Use P6 Integration API user account (not database user) to log into P6
	 * 
	 * @param args
	 * @throws ClientException
	 * @throws NetworkException
	 * @throws ServerException
	 */
	public static void main(String[] args) throws ServerException, NetworkException, ClientException {

		DatabaseInstance[] dbInstances = Session.getDatabaseInstances(A01_DBInstance.getRmiUrl());
		Session session = A02_Session.getSession(dbInstances);

		System.out.println(session);
	}

	public static Session getSession(DatabaseInstance[] dbInstances) throws ServerException, NetworkException, ClientException {
		return Session.login(A01_DBInstance.getRmiUrl(), dbInstances[p6DBIndex].getDatabaseId(), p6UserName, p6Password);
	}

	private static int p6DBIndex = 1;
	private static String p6UserName = A00_APIConfig.p6UserName;
	private static String p6Password = A00_APIConfig.p6Password;
}
