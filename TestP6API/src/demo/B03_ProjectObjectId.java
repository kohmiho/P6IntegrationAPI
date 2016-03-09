package demo;

import com.primavera.ServerException;
import com.primavera.common.value.ObjectId;
import com.primavera.integration.client.ClientException;
import com.primavera.integration.client.EnterpriseLoadManager;
import com.primavera.integration.client.Session;
import com.primavera.integration.client.bo.object.Project;
import com.primavera.integration.common.DatabaseInstance;
import com.primavera.integration.network.NetworkException;

public class B03_ProjectObjectId {

	/**
	 * If you know the primary key of a object, you can use it load it via
	 * ObjectId. For example, if you know the primary key of a project, you can
	 * use it to load this project
	 * 
	 * @param args
	 * @throws ClientException
	 * @throws NetworkException
	 * @throws ServerException
	 */
	public static void main(String[] args) throws ServerException, NetworkException, ClientException {

		DatabaseInstance[] dbInstances = Session.getDatabaseInstances(A01_DBInstance.getRmiUrl());
		Session session = A02_Session.getSession(dbInstances);
		EnterpriseLoadManager elm = session.getEnterpriseLoadManager();

		// there are two basic way to create an ObjectId
		ObjectId objectId = new ObjectId(90311);
		// objectId = ObjectId.fromString("90311");

		Project project = Project.load(session, new String[] { "Id", "Name" }, objectId);

		System.out.printf("Project[%s] : %s | %s\n", project.getObjectId(), project.getId(), project.getName());
	}
}
