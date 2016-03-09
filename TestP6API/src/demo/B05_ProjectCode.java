package demo;

import com.primavera.ServerException;
import com.primavera.integration.client.ClientException;
import com.primavera.integration.client.EnterpriseLoadManager;
import com.primavera.integration.client.Session;
import com.primavera.integration.client.bo.BOIterator;
import com.primavera.integration.client.bo.object.ProjectCode;
import com.primavera.integration.client.bo.object.ProjectCodeAssignment;
import com.primavera.integration.client.bo.object.ProjectCodeType;
import com.primavera.integration.common.DatabaseInstance;
import com.primavera.integration.network.NetworkException;

public class B05_ProjectCode {

	/**
	 * @param args
	 * @throws ClientException
	 * @throws NetworkException
	 * @throws ServerException
	 */
	public static void main(String[] args) throws ServerException, NetworkException, ClientException {

		DatabaseInstance[] dbInstances = Session.getDatabaseInstances(A01_DBInstance.getRmiUrl());
		Session session = A02_Session.getSession(dbInstances);
		EnterpriseLoadManager elm = session.getEnterpriseLoadManager();

		// load a project code type
		BOIterator<ProjectCodeType> boiProjectCodeType = elm.loadProjectCodeTypes(ProjectCodeType.getAllFields(), "ObjectId=49", null);
		while (boiProjectCodeType.hasNext()) {
			ProjectCodeType codeType = boiProjectCodeType.next();

			System.out.printf("CodeType[%s] %s\n", codeType.getObjectId(), codeType.getName());

			// load its project code values
			BOIterator<ProjectCode> boiProjectCode = codeType.loadAllProjectCodes(ProjectCode.getAllFields(), null, null);
			while (boiProjectCode.hasNext()) {
				ProjectCode code = boiProjectCode.next();

				System.out.printf("\tCode[%s] %s | %s\n", code.getObjectId(), code.getCodeValue(), code.getDescription());

				// show project code assignment for a certain code
				if (314 == code.getObjectId().toInteger().intValue()) {
					BOIterator<ProjectCodeAssignment> boiCodeProject = code.loadProjectCodeAssignments(ProjectCodeAssignment.getAllFields(), null, null);

					while (boiCodeProject.hasNext()) {
						ProjectCodeAssignment codeProject = boiCodeProject.next();

						System.out.printf("\t\tCodeAssignment[%s] %s | %s\n", codeProject.getProjectObjectId(), codeProject.getProjectId(),
								codeProject.getProjectName());
					}
				}
			}
		}
	}
}
