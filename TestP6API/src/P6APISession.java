import com.primavera.ServerException;
import com.primavera.integration.client.ClientException;
import com.primavera.integration.client.RMIURL;
import com.primavera.integration.client.Session;
import com.primavera.integration.common.DatabaseInstance;
import com.primavera.integration.network.NetworkException;

public class P6APISession {

	public static Session getSession() throws ServerException, NetworkException, ClientException {

		// Connect to LoadSpring R8.3
		DatabaseInstance[] dbInstances = Session.getDatabaseInstances(RMIURL.getRmiUrl(RMIURL.STANDARD_RMI_SERVICE, "172.17.124.19", 9099));
		return Session.login(RMIURL.getRmiUrl(RMIURL.STANDARD_RMI_SERVICE, "172.17.124.19", 9099), dbInstances[0].getDatabaseId(), "p6svc", "psix$PROD1");

		// Connect to LoadSpring R15.1
		// DatabaseInstance[] dbInstances =
		// Session.getDatabaseInstances(RMIURL.getRmiUrl(RMIURL.STANDARD_RMI_SERVICE,
		// "172.17.124.27", 9099));
		// return Session.login(RMIURL.getRmiUrl(RMIURL.STANDARD_RMI_SERVICE, "172.17.124.27", 9099), dbInstances[0].getDatabaseId(), "p6svc", "psix$PROD1");
	}
}
