import com.primavera.ServerException;
import com.primavera.integration.client.EnterpriseLoadManager;
import com.primavera.integration.client.RMIURL;
import com.primavera.integration.client.Session;
import com.primavera.integration.client.bo.BOIterator;
import com.primavera.integration.client.bo.BusinessObjectException;
import com.primavera.integration.client.bo.object.Project;
import com.primavera.integration.common.DatabaseInstance;
import com.primavera.integration.network.NetworkException;

public class TestRemoteAPI {

	public static void main(String[] args) {
		Session session = null;
		try {
			// Connect to P6 R8.3 using "172.17.124.19", 9099

			// Connect to P6 R15.1
			DatabaseInstance[] dbInstances = Session.getDatabaseInstances(RMIURL.getRmiUrl(RMIURL.STANDARD_RMI_SERVICE, "172.17.124.27", 9099));

			for (DatabaseInstance databaseInstance : dbInstances) {
				System.out.println("DatabaseId=" + databaseInstance.getDatabaseId());
				System.out.println("ActualDatabaseName=" + databaseInstance.getActualDatabaseName());
				System.out.println("DatabaseUrl=" + databaseInstance.getDatabaseUrl());
				System.out.println("DatabaseName=" + databaseInstance.getDatabaseName());
				System.out.println("*******************************************");
			}

			// Connect to R15.1
			session = Session
					.login(RMIURL.getRmiUrl(RMIURL.STANDARD_RMI_SERVICE, "172.17.124.27", 9099), dbInstances[1].getDatabaseId(), "p6svc", "psix$PROD1");

			System.out.println("Successfully login into P6 DB");

			EnterpriseLoadManager elm = session.getEnterpriseLoadManager();

			testCase01(elm);
			testCase02(elm);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null)
				session.logout();
		}

		System.exit(0);
	}

	protected static void testCase01(EnterpriseLoadManager elm) throws ServerException, NetworkException, BusinessObjectException {

		BOIterator<Project> boi = elm.loadProjects(new String[] { "Name" }, null, "Name asc");

		int count = 0;
		while (boi.hasNext()) {
			Project proj = boi.next();
			System.out.println(proj.getName());

			if (++count == 100)
				break;
		}
	}

	protected static void testCase02(EnterpriseLoadManager elm) throws ServerException, NetworkException, BusinessObjectException {
		BOIterator<Project> boi = elm.loadProjects(new String[] { "Id", "Name" }, "ObjectId in (101251,101293,101295,101296,101297,1224,1427,1997,2634,4778)",
				"ObjectId asc");

		while (boi.hasNext()) {
			Project proj = boi.next();
			System.out.printf("%s | %s | %s \n", proj.getObjectId(), proj.getId(), proj.getName());

		}
	}
}
