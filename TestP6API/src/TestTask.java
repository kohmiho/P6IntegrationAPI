import com.primavera.common.value.ObjectId;
import com.primavera.integration.client.Session;
import com.primavera.integration.client.bo.object.Activity;

public class TestTask {

	public static void main(String[] args) {
		Session session = null;
		try {
			session = P6APISession.getSession();
			// EnterpriseLoadManager elm = session.getEnterpriseLoadManager();

			Activity task = Activity.load(session, Activity.getMainFields(), new ObjectId(17602164));
			System.out.println(task.getId() + ", " + task.getName());

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null)
				session.logout();
		}

		System.exit(0);
	}
}
