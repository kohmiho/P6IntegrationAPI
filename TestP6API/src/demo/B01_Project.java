package demo;

import com.primavera.ServerException;
import com.primavera.integration.client.ClientException;
import com.primavera.integration.client.EnterpriseLoadManager;
import com.primavera.integration.client.Session;
import com.primavera.integration.client.bo.BOIterator;
import com.primavera.integration.client.bo.object.Project;
import com.primavera.integration.common.DatabaseInstance;
import com.primavera.integration.network.NetworkException;

public class B01_Project {

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

		// field to load, must match the method name getXXX()
		String[] fields = { "Id", "Name" };
		// filter, similar to SQL where clause
		String whereClause = "Id like 'C.9%'";
		// order by, similar to SQL order by
		String orderBy = "Id asc";

		// use while, .hasNext(), .next() to loop each project from BOIerator
		BOIterator<Project> boiProject = elm.loadProjects(fields, whereClause, orderBy);
		while (boiProject.hasNext()) {
			Project project = boiProject.next();

			// you must load Id, Name
			System.out.println("Project = " + project.getId() + ", " + project.getName());

			// ObjectId is the database table primary key
			// you don't need to load it
			System.out.println("Project ObjectId = " + project.getObjectId());

			// you will get error, if you did not load it
			// System.out.println(project.getDataDate());
		}
	}
}
