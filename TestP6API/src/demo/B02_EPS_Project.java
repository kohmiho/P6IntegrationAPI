package demo;

import com.primavera.ServerException;
import com.primavera.integration.client.ClientException;
import com.primavera.integration.client.EnterpriseLoadManager;
import com.primavera.integration.client.Session;
import com.primavera.integration.client.bo.BOIterator;
import com.primavera.integration.client.bo.object.EPS;
import com.primavera.integration.client.bo.object.Project;
import com.primavera.integration.common.DatabaseInstance;
import com.primavera.integration.network.NetworkException;

public class B02_EPS_Project {

	/**
	 * Enterprise Load Manager provides functions to access main P6 objects, eg:
	 * Project, Activity, Resource, EPS, WBS, Codes, UDFs.
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

		BOIterator<EPS> boiEPS = elm.loadEPS(new String[] { "Id", "Name" }, "Id like '%THP%'", null);
		while (boiEPS.hasNext()) {
			EPS eps = boiEPS.next();

			System.out.println("EPS = " + eps.getId() + ", " + eps.getName());

			BOIterator<Project> boiProject = eps.loadProjectChildren(new String[] { "Id", "Name" }, null, null);
			while (boiProject.hasNext()) {
				Project project = boiProject.next();

				System.out.printf("\tProject[%s] : %s | %s\n", project.getObjectId(), project.getId(), project.getName());
			}
		}
	}
}
